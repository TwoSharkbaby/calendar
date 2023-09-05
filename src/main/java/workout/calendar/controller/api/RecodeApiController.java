package workout.calendar.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import workout.calendar.domain.dto.RecodeDto;
import workout.calendar.domain.dto.RecodeModifyFormDto;
import workout.calendar.domain.dto.RecodeResisterFormDto;
import workout.calendar.domain.entity.Recode;
import workout.calendar.repository.RecodeRepository;
import workout.calendar.service.RecodeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/recode")
@RequiredArgsConstructor
public class RecodeApiController {

    private final RecodeService recodeService;

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecodeDto> recodeDetail(@PathVariable Long id) {
        return new ResponseEntity(recodeService.getRecode(id), HttpStatus.OK);
    }

    @PreAuthorize("principal.user.id == #recodeResisterFormDto.user.id")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@Valid @RequestBody RecodeResisterFormDto recodeResisterFormDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            Long id = recodeService.createRecode(recodeResisterFormDto);
            if (id == null) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity(HttpStatus.OK);
            }
        }
    }

    @PreAuthorize("principal.user.id == #recodeModifyFormDto.user.id")
    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modify(@Valid @RequestBody RecodeModifyFormDto recodeModifyFormDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            Long id = recodeService.modifyRecode(recodeModifyFormDto);
            if (id == null) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity(HttpStatus.OK);
            }
        }
    }

    @PreAuthorize("principal.user.id == #userId")
    @DeleteMapping(value = "/delete/{id}/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable Long userId) {
        recodeService.deleteRecode(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
