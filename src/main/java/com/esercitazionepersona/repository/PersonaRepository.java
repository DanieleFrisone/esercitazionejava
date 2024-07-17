package com.esercitazionepersona.repository;

import com.esercitazionepersona.constants.DBConstants;
import com.esercitazionepersona.model.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaRepository
{
    public static List<Persona> selectAllPersona()
    {
        List<Persona> personaList = new ArrayList<>();
        try{
            Connection connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USER, DBConstants.PPW);
            String query = "SELECT * FROM persona";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Persona b = new Persona(
                        rs.getString("email"),
                        rs.getString("nome"),
                        rs.getString("cognome")
                );
                personaList.add(b);
            }
            return personaList;

        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return new ArrayList<>();
    }



    //inserisci libro

    public static Persona inserisciPersona(Persona persona) {
        try {
            Connection connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USER, DBConstants.PPW);
            String query = "INSERT INTO Persona (EMAIL, NOME, COGNOME) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, persona.getEmail());
            statement.setString(2, persona.getNome());
            statement.setString(3, persona.getCognome());

            statement.executeUpdate(); // Esegui l'istruzione di inserimento

            System.out.println("persona inserita con successo!");
            return persona;

        } catch (SQLException ex) {
            System.out.println("Errore durante l'inserimento della persona: " + ex.getMessage());
        }
        return null;
    }


    // Metodo per selezionare le persone da email
    public static List<Persona> selezionaPersonaPerEmail(String email) {
        List<Persona> personaList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USER, DBConstants.PPW);
            String query = "SELECT * FROM Persona WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("nome"));
                System.out.println(rs.getString("email"));
                Persona b = new Persona(
                        rs.getString("email"),
                        rs.getString("nome"),
                        rs.getString("cognome")
                );
                personaList.add(b);
            }

            return personaList;
        } catch (SQLException ex) {
            System.out.println("Errore durante la selezione dei libri per genere: " + ex.getMessage());
        }
        return personaList;
    }


}
