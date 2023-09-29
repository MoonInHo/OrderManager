package com.mooninho.ordermanager.ownerapp.menu.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.menu.domain.vo.MenuName;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.mooninho.ordermanager.menu.domain.entity.QMenu.menu;

@Repository
@RequiredArgsConstructor
public class MenuQueryRepositoryImpl implements MenuQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isExistMenuName(MenuName menuName) {
        return queryFactory
                .selectOne()
                .from(menu)
                .where(menu.menuName.eq(menuName))
                .fetchFirst() != null;
    }
}
