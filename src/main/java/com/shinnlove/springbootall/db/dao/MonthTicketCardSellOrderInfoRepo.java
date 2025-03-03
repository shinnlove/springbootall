package com.shinnlove.springbootall.db.dao;

import com.shinnlove.springbootall.db.po.ConsigneeAddressInfo;
import com.shinnlove.springbootall.db.po.MonthTicketCardSellOrderInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthTicketCardSellOrderInfoRepo {

    /**
     * 生成月票定制订单、含月票基本信息、收货人信息、支付基本信息等。
     *
     * @param entity
     * @return
     */
    int insertSelective(@Param("entity") MonthTicketCardSellOrderInfoEntity entity);

    /**
     * 根据阅文订单号查询阅文订单。
     *
     * @param activityId
     * @param orderNo
     * @return
     */
    MonthTicketCardSellOrderInfoEntity queryOrderByOrderNo(@Param("activityId") String activityId,
                                                           @Param("orderNo") Long orderNo);

    /**
     * 根据定制编号查询订单。
     *
     * @param activityId
     * @param customizeNo
     * @return
     */
    MonthTicketCardSellOrderInfoEntity queryOrderByCustomizeNo(@Param("activityId") String activityId,
                                                               @Param("customizeNo") Long customizeNo);

    /**
     * 根据定制商品stubId/skuId查询订单、stubId也是全局唯一的。
     *
     * @param activityId
     * @param stubId
     * @return
     */
    MonthTicketCardSellOrderInfoEntity queryOrderByStubId(@Param("activityId") String activityId,
                                                          @Param("stubId") String stubId);

    /**
     * 根据订单号更新收货人地址(阅文页面行为)。
     *
     * @param orderNo
     * @param consigneeAddressInfo
     * @return
     */
    int updateConsigneeAddressByOrderNo(@Param("orderNo") String orderNo,
                                        @Param("consigneeAddress") ConsigneeAddressInfo consigneeAddressInfo);

    /**
     * 根据预制单号更新物流信息。
     *
     * @param customizeNo
     * @param expressNo
     * @return
     */
    int updateExpressNoByCustomizeNo(@Param("customizeNo") Long customizeNo,
                                     @Param("expressNo") String expressNo);

}