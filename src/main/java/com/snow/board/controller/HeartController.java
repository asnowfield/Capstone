package com.snow.board.controller;

import com.snow.board.dto.HeartRequestDTO;
import com.snow.board.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/upheart")
public class HeartController {

    private final HeartService heartService;

    @PostMapping
    public ResponseResult<?> insert(@RequestBody @Validated HeartRequestDTO heartRequestDTO) {
        heartService.insert(heartRequestDTO);
        return success();
    }

    @DeleteMapping
    public ResponseResult<?> delete(@RequestBody @Valid HeartRequestDTO heartRequestDTO) {
        heartService.delete(heartRequestDTO);
        return success(null);
    }

}
