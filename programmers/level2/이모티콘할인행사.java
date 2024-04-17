import java.util.*;

class Solution {
    int size;
    int[] percents, emotis, answer;
    int[][] info;
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        size = emoticons.length;
        percents = new int[size];
        info = users;
        emotis = emoticons;
        
        prem(0);
        
        return answer;
    }
    
    private void prem(int idx) {
        if (idx == size) {
            check();
            return;
        }
        
        percents[idx] = 10;
        prem(idx+1);
        percents[idx] = 20;
        prem(idx+1);
        percents[idx] = 30;
        prem(idx+1);
        percents[idx] = 40;
        prem(idx+1);
    }
    
    private void check() {
        int buyCnt = 0;
        int money = 0;

        for (int[] user: info) {
            int currMoney = 0;
            
            for (int i = 0; i < size; i++) {
                if (percents[i] < user[0]) continue;
                currMoney += (emotis[i] * (100-percents[i])) / 100;
            }
            
            if (currMoney >= user[1]) buyCnt++;
            else money += currMoney;
        }
        
        if ((answer[0] < buyCnt) || (answer[0] == buyCnt && answer[1] < money)) {
            answer[0] = buyCnt;
            answer[1] = money;
        }
    }
}
