package net.bcsoft.bcvinaino.daos;

import net.bcsoft.bcvinaino.entity.Ordini;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ThresholdDAO {
    @Select({
            "SELECT ordini.id_ordine, ordini.data_ordine, SUM(menu.prezzo * articoli_ordine.qta) AS incasso_ordine",
            "FROM ordini JOIN articoli_ordine ON ordini.id_ordine = articoli_ordine.id_ordine",
            "JOIN menu ON articoli_ordine.id_menu = menu.id_menu",
            "GROUP BY(ordini.id_ordine)",
            "HAVING SUM(menu.prezzo * articoli_ordine.qta) > #{sogliaMin}",
            "ORDER BY(incasso_ordine) DESC;"
    })
    @Results(id="sogliaordine", value = {
            @Result(column = "id_ordine", property = "idOrdine"),
            @Result(column = "data_ordine", property = "dataOrdine"),
            @Result(column = "incasso_ordine", property = "incassoOrdine")
    })
    public List<Ordini> ordineThreshold (Double sogliaMin);
}