package util;

import model.Focaccia;
import model.Incasso;
import model.Ordine;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Repository
{
    public Repository() {}

    public static List<Incasso> eseguiQueryIncassi(Connection connection) throws SQLException
    {
        List<Incasso> incassi = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("" +
                "SELECT ordini.dataora, COUNT(distinct ordini.id), SUM(menu.prezzo) " +
                "FROM ordini JOIN scontrini ON ordini.id = scontrini.idordini " +
                "   JOIN menu ON scontrini.idmenu = menu.id " +
                "WHERE ordini.dataora >= ? " +
                "GROUP BY(ordini.dataora) " +
                "ORDER BY ordini.dataora");

        Calendar cal = new GregorianCalendar();
        cal.add( Calendar.DAY_OF_MONTH, -15);
        Date sqlDate = new Date(cal.getTimeInMillis());
        statement.setDate(1, sqlDate);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
            incassi.add(new Incasso (resultSet.getTimestamp(1).toLocalDateTime(), resultSet.getInt(2), resultSet.getDouble(3)));
        }

        resultSet.close();
        statement.close();

        return incassi;
    }

    public static List<Focaccia> eseguiQueryFocacce(Connection connection) throws SQLException
    {
        List<Focaccia> focacce = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("" +
                "SELECT menu.nome, COUNT(*) " +
                "FROM menu JOIN scontrini ON menu.id = scontrini.idmenu " +
                "   JOIN ordini ON scontrini.idordini = ordini.id " +
                "WHERE ordini.dataora >= ? " +
                "GROUP BY menu.nome");
    // sono gey
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -15);
        Date sqlDate = new Date(cal.getTimeInMillis());
        statement.setDate(1, sqlDate);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
            focacce.add(new Focaccia (resultSet.getString(1), resultSet.getInt(2)));
        }

        resultSet.close();
        statement.close();

        return focacce;
    }

    public static List<Ordine> eseguiQueryOrdini(Connection connection) throws SQLException
    {
        List<Ordine> ordini = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("" +
                "SELECT ordini.id, ordini.dataora, SUM (menu.prezzo) " +
                "FROM ordini JOIN scontrini ON ordini.id = scontrini.idordini " +
                "   JOIN menu ON scontrini.idmenu = menu.id " +
                "GROUP BY ordini.id " +
                "HAVING ordini.dataora >= ? " +
                "ORDER BY ordini.id DESC ");

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        Date sqlDate = new Date(cal.getTimeInMillis());
        statement.setDate(1, sqlDate);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
            ordini.add(new Ordine (resultSet.getInt(1), resultSet.getTimestamp(2), resultSet.getDouble(3)));
        }

        resultSet.close();
        statement.close();

        return ordini;
    }

    public static List<Ordine> eseguiQuerySogliaordini(Connection connection) throws SQLException
    {
        List<Ordine> ordini = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("" +
                "SELECT ordini.id, ordini.dataora, SUM (menu.prezzo) " +
                "FROM ordini JOIN scontrini ON ordini.id = scontrini.idordini " +
                "   JOIN menu ON scontrini.idmenu = menu.id " +
                "GROUP BY ordini.id " +
                "HAVING SUM (menu.prezzo) >= ? " +
                "ORDER BY ordini.id DESC ");

        int SOGLIA = 10;
        statement.setInt(1, SOGLIA);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
            ordini.add(new Ordine (resultSet.getInt(1), resultSet.getTimestamp(2), resultSet.getDouble(3)));
        }

        resultSet.close();
        statement.close();

        return ordini;
    }
}
