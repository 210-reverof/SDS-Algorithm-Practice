import java.io.*;
import java.util.*;

// java의 정렬 배우기

class Candidate {
    int num;
    int vote;
    int time;

    public Candidate(int num, int vote, int time) {
        this.num = num;
        this.vote = vote;
        this.time = time;
    }
}

// 투표수 내림차순 321
class CandidateVoteComparator implements Comparator<Candidate> {
    @Override
    public int compare(Candidate o1, Candidate o2) {
        if(o1.vote > o2.vote) {
            return -1;
        } else if (o1.vote < o2.vote) {
            return 1;
        }
        return 0;
    }

}

// 등록시간 내림차순 321
class CandidateTimeComparator implements Comparator<Candidate> {
    @Override
    public int compare(Candidate o1, Candidate o2) {
        if(o1.time> o2.time) {
            return -1;
        } else if (o1.time < o2.time) {
            return 1;
        }
        return 0;
    }

}

class Main {
    static int N;
    static int V;
    static List<Candidate> arr = new ArrayList<Candidate>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        V = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ", false);
        for (int i = 0; i < V; i++) {
            // 투표 하나마다
            int curr = Integer.parseInt(st.nextToken());

            // 사진틀이 남아았으면
            if (arr.size() < N) {
                arr.add(new Candidate(curr, 1, i));
            } else {
                // 이미 있거나
                for (int j = 0; j < N; j++)
                    if (arr.get(i).num == curr) {
                        arr.set(i, new Candidate(curr, arr.get(i).num + 1, arr.get(i).time));
                    }

                // 새로운 후보이거나
                arr.set(2, new Candidate(curr, 1, i));
            }
        }

        Collections.sort(arr, new CandidateTimeComparator());
        Collections.sort(arr, new CandidateVoteComparator());

    }
}
