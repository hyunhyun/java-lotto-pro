package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WinRanks {
    private Map<Rank, Integer> winTotals;

    public WinRanks() {
        winTotals = new HashMap<>();
        winTotals.put(Rank.FIRST, 0);
        winTotals.put(Rank.SECOND, 0);
        winTotals.put(Rank.THIRD, 0);
        winTotals.put(Rank.FOURTH, 0);
        winTotals.put(Rank.FIFTH, 0);
    }

    public Map<Rank, Integer> getWinTotals() {
        return winTotals;
    }

    public int winningPrice(Lotto winningLotto, Lottos lottos, LottoNo bonusNumber) {
        int totalPrice = 0;
        calculateWinPriceTotals(winningLotto, lottos, bonusNumber);
        for (Rank rankEnum : winTotals.keySet()) {
            totalPrice += winTotals.get(rankEnum) * rankEnum.getWinningMoney();
        }
        return totalPrice;
    }

    public void calculateWinPriceTotals(Lotto winningLotto, Lottos lottos, LottoNo bonusNumber) {
        for (Lotto lotto : lottos.getLottoSheets()) {
            int checkMatchCount = lotto.checkMatchCount(winningLotto);
            boolean bonusMatch = lotto.checkBonusMatch(bonusNumber);
            addRankCount(checkMatchCount, bonusMatch);
        }
    }

    private void addRankCount(int checkMatchCount, boolean bonusMatch) {
        Optional<Rank> rank = Rank.matchedRank(checkMatchCount, bonusMatch);
        Rank key = null;
        if(rank.isPresent()){
            key = rank.get();
        }
        if (winTotals.containsKey(key)) {
            winTotals.put(key, winTotals.get(key) + 1);
        }
    }

    public double calulateProfitRate(int profitMoney, int purchaseMoney) {
        return (double) profitMoney / purchaseMoney;
    }
}