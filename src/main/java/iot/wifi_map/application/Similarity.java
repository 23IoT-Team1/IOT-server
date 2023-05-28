package iot.wifi_map.application;

import iot.wifi_map.application.dto.request.FindPositionRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

// 일단 weighted distance 로 평균과 분산을 사용한버전
@Component
@RequiredArgsConstructor
public class Similarity {

    public String findPosition(List<ap> dataSet,
                               FindPositionRequestDto dto,
                               Integer dataSetNum) {

        int numOfAp = 3; // 수신할 wifi 신호의 개수
        int k = 6; // KNN 알고리즘에 사용되는 k
        double revision = 0.6; // 보정값. 우리는 가장 강한 numOfAp개의 신호를 사용한다. 웬만한 경우에는 같은 위치에서 scan할 경우 가장 강한 wifi 신호도
        // 똑같겠지만, 그 순간 잠시 이상이 생겨 가장 강한 wifi의 신호가 달라질 수도 있기 때문에 보정값을 넣는다.

        testCaseRp testCase = new testCaseRp(dto); // 현재 측정된 값

        ArrayList<calc> calc_list;
        ArrayList<calc> filtered_calc_list; // filtering할 calc_list
        String calc_answer; // 가장 유사도가 높은 rp를 저장할 변수

        calc_list = calculateAvgAndVar(dataSet, testCase, dataSetNum, numOfAp);

        // 분산과 표준편차를 이용한 weightedEuclideanDistance 구하기
        weightedEuclideanDistance(calc_list, dataSet, testCase, dataSetNum, numOfAp, k);

        // 수많은 calc_list를 k개의 후보로 필터링
        filtered_calc_list = filterCalcList(calc_list, revision);


        //Weighted KNN을 사용하여 filtered_calc_list에서 K개를 뽑아서 비교 후 정답 도출
        calc_answer = weightedKNN(filtered_calc_list, k);

        return calc_answer; // grid_point 리턴

    }

    public String weightedKNN(ArrayList<calc> filtered_calc_list, int k) {

        //euclidean distance순으로 정렬하고 k개만큼 뽑은 후 count가 크고 ed가 작을수록 유사. 다만 count가 더 작은데 ed가 더 작은 경우는?

        Collections.sort(filtered_calc_list, new Comparator<calc>() {
            public int compare(calc a, calc b) {
                if (a.EuclideanDistance > b.EuclideanDistance)
                    return 1;
                else
                    return -1;
            }
        });

        // 예외처리
        List<calc> k_calc_list;
        if (filtered_calc_list.size() <= k) {
            k_calc_list = filtered_calc_list; // filtered_calc_list 크기가 k보다 작거나 같을 경우 모든 요소를 포함하는 서브리스트로 설정
        } else {
            k_calc_list = filtered_calc_list.subList(0, k); // filtered_calc_list 크기가 k보다 클 경우 시작부터 k-1까지의 요소를 포함하는 서브리스트로 설정
        }

        return k_calc_list.get(0).rp;

    }

    public ArrayList<calc> filterCalcList(ArrayList<calc> calc_list, double revisionValue) {

        ArrayList<calc> filtered_calc_list = new ArrayList<calc>();

        int maxCount = calc_list.get(0).count; // 가장 큰 count maxCount에 저장

        for (int i = 0; i < calc_list.size(); i++) {
            if (calc_list.get(i).count > maxCount * revisionValue)
                filtered_calc_list.add(calc_list.get(i));
            else
                break;
        }

        return filtered_calc_list;

    }

    public void weightedEuclideanDistance(ArrayList<calc> calc_list, List<ap> dataSet, testCaseRp testCase,
                                          Integer dataSetNum, int numOfAp, int k) {


        String[] testCaseBssid = new String[numOfAp];
        for (int i = 0; i < numOfAp; i++) {
            testCaseBssid[i] = testCase.getBssid(i);
        }

        // 저장된 데이터셋에서 데이터를 하나씩 가져온다.
        for (int i = 0; i < dataSetNum / numOfAp; i++) {
            double weightedEuclideanDistance = 0;

            for (int j = 0; j < numOfAp; j++) {
                // 현재 측정된 값에 포함된 ap가 dataset에 있는 경우
                if (Arrays.asList(testCaseBssid).contains(dataSet.get((numOfAp * i) + j).bssid)) {

                    // Weighted Euclidean distance 구함. 관측 rss값과 데이터셋 rss값의 차를 구한다음 제곱을하고 분산값으로 나누어준다
                    weightedEuclideanDistance += Math.sqrt(Math
                            .pow(Integer
                                    .parseInt(testCase.aps.get(Arrays.asList(testCaseBssid)
                                            .indexOf(dataSet.get((numOfAp * i) + j).bssid)).rss.replace("dbm", "").trim())
                                    - Integer.parseInt(dataSet.get((numOfAp * i) + j).rss.replace("dbm", "").trim()), 2)
                            / calc_list.get(i).variance);
                }
            }
            // 각 차의 합을 일치하는 ap의 갯수인 count로 나눠 평균값을 구한다.
            calc_list.get(i).EuclideanDistance = Math.round(weightedEuclideanDistance * 100) / 100.0;
        }

        // 현재 위치와의 유사도 순으로 정렬.
        calc_list.sort(Comparator.reverseOrder());

    }

    public ArrayList<calc> calculateAvgAndVar(List<ap> dataSet, testCaseRp testCase, Integer dataSetNum, int numOfAp) {
        ArrayList<calc> calc_list = new ArrayList<>();

        String[] testCaseBssid = new String[numOfAp];
        for (int i = 0; i < numOfAp; i++) {
            testCaseBssid[i] = testCase.getBssid(i);
        }

        // 저장된 데이터셋에서 데이터를 하나씩 가져온다.
        for (int i = 0; i < dataSetNum / numOfAp; i++) {
            // calc 초기화
            calc c = new calc(dataSet.get(i * numOfAp).rp);
            int sum = 0;
            double variance = 0;

            for (int j = 0; j < numOfAp; j++) {
                // 현재 측정된 값에 포함된 ap가 dataset에 있는 경우
                if (Arrays.asList(testCaseBssid).contains(dataSet.get((numOfAp * i) + j).bssid)) {

                    // 일치하는 ap의 수 count
                    c.count++;

                    // 두 rss 차의 절댓값을 구함
                    sum += Integer.parseInt(dataSet.get((numOfAp * i) + j).rss.replace("dbm", "").trim());
                }
            }

            // 예외처리
            if(c.count == 0){
                c.avg = 0;
            }else {
                // 각 차의 합을 일치하는 ap의 갯수인 count로 나눠 평균값을 구한다.
                c.avg = sum / c.count;
            }

            for (int k = 0; k < numOfAp; k++) {
                // 현재 측정된 값에 포함된 ap가 dataset에 있는 경우
                if (Arrays.asList(testCaseBssid).contains(dataSet.get((numOfAp * i) + k).bssid)) {

                    // 데이터셋 rss값에서 평균 rss값을 빼고 제곱을 한 다음
                    variance += Math
                            .pow(Integer.parseInt(dataSet.get((numOfAp * i) + k).rss.replace("dbm", "").trim()) - c.avg, 2);
                }
            }
            // 데이터셋 rss값에서 평균 rss값을 빼고 제곱을 한 후 전체 개수로 나눠준다.
            c.variance = Math.round((variance / c.count) * 100) / 100.0;
            // calc_list에 넣는다
            calc_list.add(c);

        }
        return calc_list;
    }

}

/*
 * 가장 유사한 wifi 데이터셋을 찾기위해 사용할 calc 객체.
 *
 * count는 현재 측정한 wifi 데이터와 db에서 가져온 데이터와 일치하는 ap의 수 ex)알파벳은 bssid라고 가정
 *
 * 현재 측정값 : grid : unknown , {A,-70dbm}, {C,-70dbm}
 *
 * grid: 4-1, { A, -70dbm}, { B, -70dbm}
 * grid: 4-2, { A, -50dbm}, { C, -60dbm}
 * grid: 4-3, { A, -60dbm}, { C, -60dbm}
 *
 *
 * 이 경우에는 4-1이 유사도가 0으로 가장 유사하지만 C를 수신하지 못해 count가 1이므로 제외하고 A와C를 수신해서 count가 2인
 * 4-2와 4-3중에서 더 유사도가 높은 4-3으로 추정 다만 AP의 개수가 많아지고 데이터셋의 개수가 많아질수록 count가 더 적더라도
 * 유사도가 더 높다면 보정값을 넣어 그것을 선택해야 될 거 같음.
 */
class calc implements Comparable<calc> {
    String rp = null;
    int count = 0;
    double avg = 0;
    double EuclideanDistance = 0;
    double variance = 0;

    public calc(String rp) {
        this.rp = rp;
        this.count = 0;
        this.avg = 0;
        this.variance = 0;
        this.EuclideanDistance = 0;
    }

    // DB에 저장된 wifi rss 데이터셋을 가져와서 현재 위치와의 유사도순으로 정렬할 때 사용.
    @Override
    public int compareTo(calc c) {
        if (c.count < count) {
            return 1;
        } else if (c.count > count) {
            return -1;
        } else {
            if (c.EuclideanDistance > EuclideanDistance)
                return 1;
            else if (c.EuclideanDistance < EuclideanDistance)
                return -1;
        }
        return 0;
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
class ap {
    String ssid;
    String bssid;
    String rss;
    String rp = null;
    String place = null;

    public ap(String ssid,
              String bssid,
              String rss) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.rss = rss;
    }
}

// 알고싶은 현재 위치에 대한 정보..
class testCaseRp {

    List<ap> aps;

    public testCaseRp(FindPositionRequestDto dto) {

        // 현재 측정되는 ap List 생성
        this.aps = dto.getAps().stream()
                .map(ap -> new ap(
                                ap.getSsid(),
                                ap.getBssid(),
                                ap.getRss()
                        )
                )
                .collect(Collectors.toList());
    }

    public String getBssid(int index) {
        return aps.get(index).bssid;
    }

}