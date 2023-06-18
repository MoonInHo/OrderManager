package com.order.manager.repository;

import com.order.manager.domain.wrapper.account.Phone;
import com.order.manager.domain.wrapper.account.VerificationCode;
import com.order.manager.enums.VerificationStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.order.manager.domain.entity.QVerification.verification;

@Repository
@RequiredArgsConstructor
public class VerificationQueryRepository {

    private final JPAQueryFactory queryFactory;

    public boolean verificationExist(Phone phone) {
        Integer result = queryFactory
                .selectOne()
                .from(verification)
                .where(verification.phone.eq(phone))
                .fetchFirst();

        return result != null;
    }

    public boolean notMatchedVerificationCode(Phone phone, VerificationCode verificationCode) {
        Integer result = queryFactory
                .selectOne()
                .from(verification)
                .where(verification.phone.eq(phone), verification.verificationCode.eq(verificationCode))
                .fetchFirst();

        return result == null;
    }

    public void updateVerificationStatus(Phone phone) {
        queryFactory
                .update(verification)
                .set(verification.verificationStatus, VerificationStatus.SUCCESS)
                .where(verification.phone.eq(phone))
                .execute();
    }

    public void deleteVerificationByPhone(Phone phone) {
        queryFactory
                .delete(verification)
                .where(verification.phone.eq(phone))
                .execute();
    }

    public boolean isVerificationInfoNotExist(Phone phone) {
        Integer result = queryFactory
                .selectOne()
                .from(verification)
                .where(verification.phone.eq(phone), verification.verificationStatus.eq(VerificationStatus.SUCCESS))
                .fetchFirst();

        return result == null;
    }
}
