package hackathon.dev.authservice.controller;

import hackathon.dev.authservice.domain.ZResponse;
import hackathon.dev.authservice.model.Tier;
import hackathon.dev.authservice.repo.TierRepo;
import hackathon.dev.authservice.service.TierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiers")
@CrossOrigin
@AllArgsConstructor
public class TierController {

    private final TierService tierService;
    private final TierRepo tierRepo;

    @PostMapping("/default")
    public ResponseEntity<ZResponse<List<Tier>>> createDefaultTiersByProfessionId(
            @RequestParam("professionId") Long professionId
    ){
        List<Tier> resultList = tierService.saveDefaultTiersByProfessionId(professionId);
        return new ResponseEntity(
                ZResponse.<List<Tier>>builder().success(true).code(HttpStatus.CREATED.value()).message("Successfully saved").data(resultList).build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ZResponse<List<Tier>>> getTiersByProfessionId(@RequestParam("professionId") Long professionId){
        List<Tier> resultList = tierRepo.getByProfessionId(professionId);
        return new ResponseEntity(
                ZResponse.<List<Tier>>builder().success(true).code(HttpStatus.CREATED.value()).message("Successfully saved").data(resultList).build(),
                HttpStatus.CREATED
        );
    }
}
