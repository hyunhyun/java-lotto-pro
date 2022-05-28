package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private List<LottoNo> lottoNumbers = new ArrayList<>();

    public Lotto() {
    }

    public Lotto(String[] splitWinningLotto) {
        if (splitWinningLotto == null || splitWinningLotto.length != LottoConst.LOTTO_NO_SIZE) {
            String exceptionMessage = String.format("로또는 %d개의 숫자입니다.", LottoConst.LOTTO_NO_SIZE);
            throw new IllegalArgumentException(exceptionMessage);
        }
        for (String s : splitWinningLotto) {
            LottoNo lottoNo = new LottoNo(s.trim());
            addLottoNumber(lottoNo);
        }
    }

    public static Lotto createRandomLotto(List<Integer> lottoNumbers) {
        Lotto lotto = new Lotto();
        for (Integer lottoNumber : lottoNumbers) {
            lotto.getLottoNumbers().add(new LottoNo(lottoNumber));
        }
        return lotto;
    }

    public List<LottoNo> getLottoNumbers() {
        return lottoNumbers;
    }

    public int checkMatchCount(Lotto checkLotto) {
        int countMatch = 0;

        for (LottoNo lottoNo : checkLotto.getLottoNumbers()) {
            countMatch = lottoNumbers.contains(lottoNo) ? countMatch + 1 : countMatch;
        }
        return countMatch;
    }

    public void addLottoNumber(LottoNo lottoNo) {
        if (lottoNumbers.contains(lottoNo)) {
            throw new IllegalArgumentException("이미 존재하는 번호는 중복 될 수 없습니다.");
        }
        lottoNumbers.add(lottoNo);
    }

    public boolean checkBonusMatch(LottoNo bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }
}