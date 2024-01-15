package com.octopus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.octopus.base.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 型号库存快照
 * </p>
 *
 * @author fd
 * @since 2024-01-12
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sas_ivt_shelf_model_snap_shot")
public class ShelfModelSnapShot extends BaseEntity {

    /**
     * 商城ID
     */
    @TableField("SHOP_ID")
    private Integer shopId;

    /**
     * 接管商城ID
     */
    @TableField("TAKEOVER_SHOP_ID")
    private Integer takeoverShopId;

    /**
     * 仓库ID
     */
    @TableField("WAREHOUSE_ID")
    private Integer warehouseId;

    /**
     * 接管仓库ID
     */
    @TableField("TAKEOVER_WAREHOUSE_ID")
    private Integer takeoverWarehouseId;

    /**
     * SCMID
     */
    @TableField("SCM_ID")
    private Integer scmId;

    /**
     * SMID
     */
    @TableField("SM_ID")
    private Integer smId;

    /**
     * 产品ID
     */
    @TableField("MODEL_ID")
    private Integer modelId;

    /**
     * 产品名
     */
    @TableField("MODEL_NAME")
    private String modelName;

    /**
     * 订货号
     */
    @TableField("MATERIAL_NO")
    private String materialNo;

    /**
     * 品牌名
     */
    @TableField("BRAND_NAME")
    private String brandName;

    /**
     * 初始数量
     */
    @TableField("TOTAL_QUANTITY")
    private BigDecimal totalQuantity;

    /**
     * 库存数量
     */
    @TableField("AVAILABLE_QUANTITY")
    private BigDecimal availableQuantity = BigDecimal.valueOf(0.00);

    /**
     * 冻结数量
     */
    @TableField("FROZEN_QUANTITY")
    private BigDecimal frozenQuantity;

    /**
     * 盘点数量
     */
    @TableField("COUNTED_QUANTITY")
    private BigDecimal countedQuantity;

    @TableField("SHELF_QUANTITY")
    private Long shelfQuantity;

    /**
     * 盘盈数量
     */
    @TableField("PROFIT_QUANTITY")
    private Integer profitQuantity;

    /**
     * 盘亏数量
     */
    @TableField("LOST_QUANTITY")
    private Integer lostQuantity;

    /**
     * 调拨冻结数量
     */
    @TableField("TRANSFERRED_QUANTITY")
    private Integer transferredQuantity;

    /**
     * 入库单主键
     */
    @TableField("STOCK_IN_ID")
    private Integer stockInId;

    /**
     * 入库号
     */
    @TableField("STOCK_IN_NO")
    private String stockInNo;

    /**
     * 入库类型
     */
    @TableField("STOCK_IN_TYPE")
    private Integer stockInType;

    /**
     * 入库明细ID
     */
    @TableField("STOCK_IN_DETAIL_ID")
    private Integer stockInDetailId;

    /**
     * 货架库存ID
     */
    @TableField("SHELF_MODEL_ID")
    private Integer shelfModelId;

    /**
     * 库位ID
     */
    @TableField("SHELF_ID")
    private Integer shelfId;

    /**
     * 状态
     */
    @TableField("`STATUS`")
    private Byte status;

    /**
     * 上架时间
     */
    @TableField("INVENTORY_SHELF_AT")
    private LocalDateTime inventoryShelfAt;

    /**
     * 原始上架时间
     */
    @TableField("ORIGINAL_SHELF_AT")
    private LocalDateTime originalShelfAt;

    /**
     * 快照日期
     */
    @TableField("INVENTORY_DATE")
    private LocalDate inventoryDate;

    /**
     * 采购渠道
     */
    @TableField("PURCHASE_CHANNEL")
    private Byte purchaseChannel;

    /**
     * 采购价
     */
    @TableField("PURCHASE_PRICE")
    private BigDecimal purchasePrice;

    /**
     * 预估价
     */
    @TableField("ESTIMATED_PRICE")
    private BigDecimal estimatedPrice;

    /**
     * 修正采购单价
     */
    @TableField("AMEND_PURCHASE_PRICE")
    private BigDecimal amendPurchasePrice;

    /**
     * 修正预估单价
     */
    @TableField("AMEND_ESTIMATED_PRICE")
    private BigDecimal amendEstimatedPrice;

    /**
     * 供应单价
     */
    @TableField("SUPPLY_PRICE")
    private BigDecimal supplyPrice;

    /**
     * 是否供应
     */
    @TableField("SUPPLY_ABLE")
    private Boolean supplyAble;

    /**
     * 系统是否启用供应
     */
    @TableField("SYSTEM_ON")
    private Boolean systemOn;

    /**
     * S是否启用供应
     */
    @TableField("COMPANY_ON")
    private Boolean companyOn;

    /**
     * 创建时间
     */
    @TableField("CREATED_AT")
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    @TableField("UPDATED_AT")
    private LocalDateTime updatedAt;
}
