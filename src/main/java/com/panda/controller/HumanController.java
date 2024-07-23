package com.panda.controller;

import com.panda.dto.HumanDto;
import com.panda.service.HumanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/humans")
public class HumanController {

    private final HumanService humanService;

    @PostMapping("/add-default")
    public void addDefault() {
        humanService.addDefault();
    }

    @GetMapping("/get-all-humans")
    public List<HumanDto> getAllHumansDto() {
        return humanService.getAllHumansDto();
    }

    @PostMapping("/add-human")
    public HumanDto addNewHuman(@RequestBody HumanDto humanDto) {
        return humanService.addHuman(humanDto);
    }

    @GetMapping("/get-human-by-id{id}")
    public HumanDto getHumanById(@RequestParam UUID id) {
        return humanService.getHumanById(id);
    }

    @PutMapping("/update-human")
    public HumanDto updateHuman(@RequestBody HumanDto humanDto) {
        return humanService.updateHuman(humanDto);
    }
}
