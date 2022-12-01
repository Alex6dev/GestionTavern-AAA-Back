package aaa.tavern.entity;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "table_rest")
public class TableRest {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTable;

    @Column(name = "number_place")
    private Integer numberPlace;

    private Float hygiene;

    @Column(name = "pos_x")
    private Float posX;

    @Column(name = "pos_y")
    private Float posY;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    public TableRest(Integer numberPlace, Float hygiene, Float posX, Float posY, Place place) {
        this.numberPlace = numberPlace;
        this.hygiene = hygiene;
        this.posX = posX;
        this.posY = posY;
        this.place = place;
    }

    public TableRest() {
    }

    /**
     * Deux Tables sont les mêmes si ils ont le même identifiant.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        TableRest that = (TableRest) o;
        return Objects.equals(idTable, that.idTable);
    }

    /**
     * L'identifiant définit le hash.
     */
    @Override
    public int hashCode() {
        return idTable.hashCode();
    }

    /*
     * public boolean tableOccupied(){
     * int nbCustomer = 0 ;
     * String sql = "" ;
     * try{
     * sql = "SELECT COUNT(*) FROM customer as c"
     * +" INNER JOIN `table` as t"
     * +" ON t.id_table = c.id_table"
     * +" INNER JOIN place as p"
     * +" ON t.id_place = p.id_place"
     * +" WHERE c.id_table = "+this.tableId+" ;" ;
     * ResultSet resultat = DBManager.execute(sql) ;
     * if(resultat.next()){
     * nbCustomer = resultat.getInt("COUNT(*)") ;
     * if(nbCustomer > 0){
     * return true ;
     * }else{
     * return false ;
     * }
     * }else{
     * return false ;
     * }
     * }catch(SQLException ex) {
     * System.out.println("SQLException" + ex.getMessage());
     * System.out.println("SQLState" + ex.getSQLState());
     * System.out.println("VendorError"+ ex.getErrorCode());
     * return false ;
     * }
     * }
     * 
     * public boolean tableIsReserved(){
     * int nbReservation = 0 ;
     * int idCustomer = 0 ;
     * String sql = "" ;
     * try{
     * sql = "SELECT id_customer FROM customer as c"
     * +" INNER JOIN `table` as t"
     * +" ON t.id_table = c.id_table"
     * +" INNER JOIN place as p"
     * +" ON t.id_place = p.id_place"
     * +" WHERE c.id_table = "+this.tableId+" ;" ;
     * ResultSet resultat = DBManager.execute(sql) ;
     * if(resultat.next()){
     * idCustomer = resultat.getInt("id_customer") ;
     * sql = "SELECT COUNT(*) FROM reservation as r"
     * +" INNER JOIN customer as c"
     * +" ON c.id_customer = r.id_customer"
     * +" WHERE r.id_customer = "+ idCustomer ;
     * resultat = DBManager.execute(sql) ;
     * if(resultat.next()){
     * nbReservation = resultat.getInt("COUNT(*)") ;
     * if(nbReservation > 0){
     * return true ;
     * }else{
     * return false ;
     * }
     * }else{
     * return false ;
     * }
     * }else{
     * return false ;
     * }
     * }catch(SQLException ex) {
     * System.out.println("SQLException" + ex.getMessage());
     * System.out.println("SQLState" + ex.getSQLState());
     * System.out.println("VendorError"+ ex.getErrorCode());
     * return false ;
     * }
     * }
     * public int numberOfSeatsAvailable(){ //retourne le nombre de places
     * disponibles sur la table
     * int nbSeatsOccupied = 0 ;
     * if(this.tableOccupied()){
     * try{
     * ResultSet resultat = DBManager.execute("SELECT COUNT(*) FROM customer as c"
     * +" INNER JOIN `table` as t"
     * +" ON t.id_table = c.id_table"
     * +" INNER JOIN place as p"
     * +" ON t.id_place = p.id_place"
     * +" WHERE c.id_table = "+this.tableId+" ;") ;
     * if(resultat.next()){
     * nbSeatsOccupied = resultat.getInt("COUNT(*)") ;
     * return this.numberPlace - nbSeatsOccupied ;
     * }else{
     * return 0 ;
     * }
     * }catch(SQLException ex) {
     * System.out.println("SQLException" + ex.getMessage());
     * System.out.println("SQLState" + ex.getSQLState());
     * System.out.println("VendorError"+ ex.getErrorCode());
     * return 0 ;
     * }
     * }else{
     * return this.numberPlace ;
     * }
     * }
     */
    // #region
    public Integer getIdTable() {
        return idTable;
    }

    public void setIdTable(Integer tableId) {
        this.idTable = tableId;
    }

    public Integer getNumberPlace() {
        return numberPlace;
    }

    public void setNumberPlace(Integer numberPlace) {
        this.numberPlace = numberPlace;
    }

    public Float getHygiene() {
        return hygiene;
    }

    public void setHygiene(Float hygiene) {
        this.hygiene = hygiene;
    }

    public Float getPosX() {
        return posX;
    }

    public void setPosX(Float posX) {
        this.posX = posX;
    }

    public Float getPosY() {
        return posY;
    }

    public void setPosY(Float posY) {
        this.posY = posY;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    // #endregion
}
