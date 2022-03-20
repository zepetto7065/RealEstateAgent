package me.sangmoon.RealEstateAgent.repository;

import lombok.RequiredArgsConstructor;
import me.sangmoon.RealEstateAgent.domain.room.Room;
import me.sangmoon.RealEstateAgent.domain.room.RoomType;
import me.sangmoon.RealEstateAgent.dto.SearchDto;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomSearchRepository {

    private final EntityManager em;

    public List<Room> findAllBySearch(SearchDto searchDto) {
        String jpql = "select r from Room r";
        boolean isFirstCondition = true;

        //방유형
        if (searchDto.getRoomType() != null && !RoomType.ALL.equals(searchDto.getRoomType())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " r.roomType = : roomType";
        }

        //거래유형
        if (StringUtils.hasText(searchDto.getPayType())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " r.payType like :payType";
        }

        //가격범위
        if (searchDto.getMaxDeposit() > 0) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " r.deposit <= :maxPrice";
        }

        if (searchDto.getMinDeposit() > 0) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " r.deposit >= :minPrice";
        }

        TypedQuery<Room> query = em.createQuery(jpql, Room.class)
                .setMaxResults(1000);

        if (searchDto.getRoomType() != null && !RoomType.ALL.equals(searchDto.getRoomType()))
            query.setParameter("roomType", searchDto.getRoomType());
        if (searchDto.getPayType() != null) query.setParameter("payType", searchDto.getPayType());
        if (searchDto.getMaxDeposit() > 0) query.setParameter("maxPrice", searchDto.getMaxDeposit());
        if (searchDto.getMinDeposit() > 0) query.setParameter("minPrice", searchDto.getMinDeposit());

        return query.getResultList();

    }
}
