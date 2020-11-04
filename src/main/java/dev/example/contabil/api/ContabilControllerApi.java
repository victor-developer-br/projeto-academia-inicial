package dev.example.contabil.api;

import dev.example.contabil.service.ContabilService;
import dev.example.domain.dto.ContabilDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/contabil")
public class ContabilControllerApi {

    @Autowired
    private ContabilService contabilService;

    @GetMapping("/{id}")
    public ResponseEntity<ContabilDTO> findById(@PathVariable Integer id,
                                                @RequestParam(value = "startdate", defaultValue = "") String startDate,
                                                @RequestParam(value = "enddate", defaultValue = "") String endDate)
    {
       ContabilDTO obj = (startDate.equals("") || endDate.equals("") ) ? contabilService.contabilById(id) : contabilService.contabilByIdAndDate(id, startDate, endDate);
        return ResponseEntity.ok().body(obj);
    }
    
}
