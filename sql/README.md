# sql 文件
本目录(sql)中包含项目所需的所有 sql 文件，对于 spring cloud alibaba seata 组件配置使用国中涉及的数据库表，请按照 seata 官方教程配置，本目录中没有提供哈，seata配置请移除 [seata官方 github 仓库](https://github.com/seata/seata_samples)..
部分数据库需要进行分布分表，本项目中是2库2表，对于需要分库分表操作的数据库和数据表，数据库和数据表名分表加上 0 和 1 即可。sweetcat_market_user_info{0, 1}.t_userinfo{0, 1}，需要分库分表操作的 sql 文件名如下：
* sweetcat_market_app_carousel
* sweetcat_market_app_couponcenter
* sweetcat_market_app_credit
* sweetcat_market_app_customerservice
* sweetcat_market_store_commodity
* sweetcat_market_store_info
* sweetcat_market_store_order
* sweetcat_market_store_secondkill
* sweetcat_market_takeaway_order
* sweetcat_market_user_commment
* sweetcat_market_user_coupon
* sweetcat_market_user_favorite
* sweetcat_market_user_footprint
* sweetcat_market_user_userinfo
* sweetcat_market_user_information
* sweetcat_market_user_order
* sweetcat_market_user_recommend
* sweetcat_market_user_relationship