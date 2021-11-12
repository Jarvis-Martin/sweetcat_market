package com.sweetcat.favorite.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.favorite.application.command.AddUserFavoriteCommand;
import com.sweetcat.favorite.domain.favorite.entity.UserFavorate;
import com.sweetcat.favorite.interfaces.facade.UserFootprintFacade;
import com.sweetcat.favorite.interfaces.facade.assembler.UserFavoriteAssembler;
import com.sweetcat.favorite.interfaces.facade.restdto.UserFavoriteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-10:47
 * @version: 1.0
 */
@RestController
@RequestMapping("/favorite")
public class UserFavoriteRestController {
    private UserFootprintFacade footprintFacade;
    private UserFavoriteAssembler favoriteAssembler;

    @Autowired
    public void setFavoriteAssembler(UserFavoriteAssembler favoriteAssembler) {
        this.favoriteAssembler = favoriteAssembler;
    }

    @Autowired
    public void setFootprintFacade(UserFootprintFacade footprintFacade) {
        this.footprintFacade = footprintFacade;
    }

    /**
     * 添加收藏商品记录
     *
     * @param command command
     */
    @PostMapping("/add")
    public ResponseDTO addOne(AddUserFavoriteCommand command) {
        footprintFacade.addOne(command);
        return response("收藏商品成功", "{}");
    }

    /**
     * 删除收藏的商品
     *
     * @param favoriteId favoriteId
     */
    @DeleteMapping("/{favorite_id}")
    public ResponseDTO deleteOne(@PathVariable("favorite_id") Long favoriteId) {
        footprintFacade.deleteOne(favoriteId);
        return response("删除商品成功", "{}");
    }

    /**
     * 查询 用户id 为 userId的用户的收藏夹分页数据
     * @param userid userid
     * @param page page
     * @param limit limit
     * @return
     */
    @GetMapping("/{user_id}")
    public ResponseDTO findPageByUserId(@PathVariable("user_id") Long userid, @RequestParam("_page") Integer page, @RequestParam("_limit") Integer limit) {
        // 查
        List<UserFavorate> userFavoritePage = footprintFacade.findPageByUserId(userid, page, limit);
        // 创建 response data
        HashMap<String, Object> dataSection = new HashMap<>(16);
        // do 转 dto
        ArrayList<UserFavoriteDTO> userFavoriteDTOPage = userFavoritePage.stream().collect(
                ArrayList<UserFavoriteDTO>::new,
                (con, userFavorate) -> con.add((favoriteAssembler.converterToUserFavoriteDTO(userFavorate))),
                ArrayList<UserFavoriteDTO>::addAll
        );
        // 组装 dto
        dataSection.put("favorite_commodities", userFavoriteDTOPage);
        // 响应
        return response("查询成功", dataSection);
    }

    /**
     * 通用的放回 ResponseDTO
     *
     * @param tip  用户提示信息
     * @param data 数据部分
     * @return ResponseDTO
     */
    private ResponseDTO response(String tip, Object data) {
        return new ResponseDTO(
                ResponseStatusEnum.SUCCESS.getErrorCode(),
                ResponseStatusEnum.SUCCESS.getErrorMessage(),
                tip,
                data
        );
    }
}
