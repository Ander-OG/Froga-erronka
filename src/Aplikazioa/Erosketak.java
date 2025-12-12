package Aplikazioa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Erosketak {

	
	//Langilea
	public void erosketaKudeatu3(Scanner sc) {
		System.out.println("\nHau dira erosketen aukerak:");
		System.out.println("1. Erosketak bistaratu");
		System.out.print("Zure aukera: ");
		int ErosketaLangilea3 = sc.nextInt();
		switch (ErosketaLangilea3) {
		case 1:
			erosketakErakutsi();
			break;
		default:
			System.out.println("Aukera okerra sartu duzu. Saiatu berriro!");
		}
	}

	
	//Kudeatzailea eta arduraduna
	public void erosketaKudeatu(Scanner sc) {
		System.out.println("\nHauek dira erosketen aukerak:");
		System.out.println("1. Erosketak bistaratu");
		System.out.println("2. Erosketa txertatu");
		System.out.print("Zure aukera: ");
		int ErosketaLangilea = sc.nextInt();
		switch (ErosketaLangilea) {
		case 1:
			erosketakErakutsi();
			break;
		case 2:
			erosketakSartu();
			break;
		default:
			System.out.println("Aukera okerra sartu duzu. Saiatu berriro!");
		}
	}
	public void erosketakErakutsi () {
		Conexioa conexion = new Conexioa();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM erosketak");
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String produktua = rs.getString(2);
				String kondizioa = rs.getString(3);
				Double kantitatea = rs.getDouble(4);
				String data = rs.getString(5);
				String hornitzailea = rs.getString(6);

				
				
				System.out.println(id+" - "+produktua+" - "+kondizioa+" - "+kantitatea+" - "+data+ " - " +hornitzailea);
			}
			
		} catch (SQLException e) {
		
		} finally {
			try {
				if (rs!= null) {
					rs.close();
				}
				
				if (stm!=null) {
					stm.close();
				}
				
				if (cn!=null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public void erosketakSartu () {

        Scanner sc = new Scanner(System.in);

        System.out.print("Sartu produktua: ");
        String produktua = sc.next();

        System.out.print("Sartu kondizioa: ");
        String kondizioa = sc.next();
        
        System.out.print("Sartu kantitatea: ");
        int kantitatea = sc.nextInt();
        

        System.out.print("Sartu data (yyyy-MM-dd): ");
        String dataStr = sc.next();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = null;
        try {
            utilDate = sdf.parse(dataStr);
        } catch (ParseException e) {
            System.out.println("Data formatua ez da zuzena. Erabili yyyy-MM-dd formatua.");
            return;
        }
        java.sql.Date data = new java.sql.Date(utilDate.getTime());
	
        
        System.out.print("Sartu hornitzailea: ");
        String hornitzailea = sc.next();


        Conexioa conexion = new Conexioa();
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = conexion.conectar();


            String sql = "INSERT INTO erosketak (produktua, kondizioa, kantitatea, data, hornitzailea) VALUES (?, ?, ?, ?, ?)";

            ps = cn.prepareStatement(sql);
           
            ps.setString(1, produktua);
            ps.setString(2, kondizioa);
            ps.setInt(3, kantitatea);
            ps.setDate(4, data);
            ps.setString(5, hornitzailea);
           

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Erosketa ondo gehitu da datu-basean.");
            } else {
                System.out.println("Ez da erosketa gehitu.");
            }

        } catch (SQLException e) {
            System.out.println("Errorea datuak sartzean: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
