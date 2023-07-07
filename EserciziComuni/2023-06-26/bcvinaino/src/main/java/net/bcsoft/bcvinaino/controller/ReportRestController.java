package net.bcsoft.bcvinaino.controller;

import net.bcsoft.bcvinaino.entity.IncassoGiornaliero;
import net.bcsoft.bcvinaino.entity.IncassoOrdine;
import net.bcsoft.bcvinaino.entity.QuantitaFocaccia;
import net.bcsoft.bcvinaino.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bcvinaino/report")
public class ReportRestController {
    private final ReportService reportService;

    public ReportRestController(final ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/incassi")
    public List<IncassoGiornaliero> calcolaIncassi() {
        return reportService.calcolaIncassi();
    }

    @GetMapping("/tipoFocaccia")
    public List<QuantitaFocaccia> calcolaTipoFocaccia() {
        return reportService.calcolaTipoFocaccia();
    }

    @GetMapping("/ordini")
    public List<IncassoOrdine> calcolaOrdini7Giorni() {
        return reportService.calcolaOrdini7Giorni();
    }

    @GetMapping("/soglia")
    public String calcolaSoglia() {
        return reportService.calcolaSoglia();
    }


}
