package net.bcsoft.bcvinaino.controller;

import net.bcsoft.bcvinaino.entity.IncassoGiornaliero;
import net.bcsoft.bcvinaino.entity.IncassoOrdine;
import net.bcsoft.bcvinaino.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ReportRestController {
    private final ReportService reportService;

    public ReportRestController(final ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/bcvinaino/report/incassi")
    public Map <Date, Double> calcolaIncassi() {
        return reportService.calcolaIncassi();
    }

    @GetMapping("/bcvinaino/report/tipoFocaccia")
    public Map<String, Integer> calcolaTipoFocaccia(){ return reportService.calcolaTipoFocaccia();}

    @GetMapping("/bcvinaino/report/ordini")
    public List<IncassoOrdine> calcolaOrdini7Giorni(){
        return reportService.calcolaOrdini7Giorni();
    }

}
