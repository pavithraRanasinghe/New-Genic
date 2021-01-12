package lk.robot.newgenic.entity;

import javax.persistence.*;

@Entity
@Table(name = "delivery_cost")
public class DeliveryCostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_cost_id")
    private long deliveryCostId;
    @Column
    private double galle;
    @Column
    private double matara;
    @Column
    private double hambantota;
    @Column
    private double kaluthra;
    @Column
    private double colombo;
    @Column
    private double gampaha;
    @Column
    private double puttalm;
    @Column
    private double kurunegala;
    @Column
    private double jaffna;
    @Column
    private double kilinochchi;
    @Column
    private double mannar;
    @Column
    private double mullaitivu;
    @Column
    private double vavuniya;
    @Column
    private double anuradhapura;
    @Column
    private double polonnaruwa;
    @Column
    private double matale;
    @Column
    private double kandy;
    @Column(name = "nuwara_eliya")
    private double nuwaraEliya;
    @Column
    private double ratnapura;
    @Column
    private double kegalle;
    @Column
    private double trincomalee;
    @Column
    private double batticaloa;
    @Column
    private double ampara;
    @Column
    private double monaragala;
    @Column
    private double badulla;
    @OneToOne(mappedBy = "deliveryCostEntity")
    private DeliveryEntity deliveryEntity;

    public DeliveryCostEntity() {
    }

    public DeliveryCostEntity(long deliveryCostId,
                              double galle,
                              double matara,
                              double hambantota,
                              double kaluthra,
                              double colombo,
                              double gampaha,
                              double puttalm,
                              double kurunegala,
                              double jaffna,
                              double kilinochchi,
                              double mannar,
                              double mullaitivu,
                              double vavuniya,
                              double anuradhapura,
                              double polonnaruwa,
                              double matale,
                              double kandy,
                              double nuwaraEliya,
                              double ratnapura,
                              double kegalle,
                              double trincomalee,
                              double batticaloa,
                              double ampara,
                              double monaragala,
                              double badulla) {
        this.deliveryCostId = deliveryCostId;
        this.galle = galle;
        this.matara = matara;
        this.hambantota = hambantota;
        this.kaluthra = kaluthra;
        this.colombo = colombo;
        this.gampaha = gampaha;
        this.puttalm = puttalm;
        this.kurunegala = kurunegala;
        this.jaffna = jaffna;
        this.kilinochchi = kilinochchi;
        this.mannar = mannar;
        this.mullaitivu = mullaitivu;
        this.vavuniya = vavuniya;
        this.anuradhapura = anuradhapura;
        this.polonnaruwa = polonnaruwa;
        this.matale = matale;
        this.kandy = kandy;
        this.nuwaraEliya = nuwaraEliya;
        this.ratnapura = ratnapura;
        this.kegalle = kegalle;
        this.trincomalee = trincomalee;
        this.batticaloa = batticaloa;
        this.ampara = ampara;
        this.monaragala = monaragala;
        this.badulla = badulla;
    }

    @Override
    public String toString() {
        return "DeliveryCost{" +
                "deliveryCostId=" + deliveryCostId +
                ", galle=" + galle +
                ", matara=" + matara +
                ", hambantota=" + hambantota +
                ", kaluthra=" + kaluthra +
                ", colombo=" + colombo +
                ", gampaha=" + gampaha +
                ", puttalm=" + puttalm +
                ", kurunegala=" + kurunegala +
                ", jaffna=" + jaffna +
                ", kilinochchi=" + kilinochchi +
                ", mannar=" + mannar +
                ", mullaitivu=" + mullaitivu +
                ", vavuniya=" + vavuniya +
                ", anuradhapura=" + anuradhapura +
                ", polonnaruwa=" + polonnaruwa +
                ", matale=" + matale +
                ", kandy=" + kandy +
                ", nuwaraEliya=" + nuwaraEliya +
                ", ratnapura=" + ratnapura +
                ", kegalle=" + kegalle +
                ", trincomalee=" + trincomalee +
                ", batticaloa=" + batticaloa +
                ", ampara=" + ampara +
                ", monaragala=" + monaragala +
                ", badulla=" + badulla +
                '}';
    }

    public long getDeliveryCostId() {
        return deliveryCostId;
    }

    public void setDeliveryCostId(long deliveryCostId) {
        this.deliveryCostId = deliveryCostId;
    }

    public double getGalle() {
        return galle;
    }

    public void setGalle(double galle) {
        this.galle = galle;
    }

    public double getMatara() {
        return matara;
    }

    public void setMatara(double matara) {
        this.matara = matara;
    }

    public double getHambantota() {
        return hambantota;
    }

    public void setHambantota(double hambantota) {
        this.hambantota = hambantota;
    }

    public double getKaluthra() {
        return kaluthra;
    }

    public void setKaluthra(double kaluthra) {
        this.kaluthra = kaluthra;
    }

    public double getColombo() {
        return colombo;
    }

    public void setColombo(double colombo) {
        this.colombo = colombo;
    }

    public double getGampaha() {
        return gampaha;
    }

    public void setGampaha(double gampaha) {
        this.gampaha = gampaha;
    }

    public double getPuttalm() {
        return puttalm;
    }

    public void setPuttalm(double puttalm) {
        this.puttalm = puttalm;
    }

    public double getKurunegala() {
        return kurunegala;
    }

    public void setKurunegala(double kurunegala) {
        this.kurunegala = kurunegala;
    }

    public double getJaffna() {
        return jaffna;
    }

    public void setJaffna(double jaffna) {
        this.jaffna = jaffna;
    }

    public double getKilinochchi() {
        return kilinochchi;
    }

    public void setKilinochchi(double kilinochchi) {
        this.kilinochchi = kilinochchi;
    }

    public double getMannar() {
        return mannar;
    }

    public void setMannar(double mannar) {
        this.mannar = mannar;
    }

    public double getMullaitivu() {
        return mullaitivu;
    }

    public void setMullaitivu(double mullaitivu) {
        this.mullaitivu = mullaitivu;
    }

    public double getVavuniya() {
        return vavuniya;
    }

    public void setVavuniya(double vavuniya) {
        this.vavuniya = vavuniya;
    }

    public double getAnuradhapura() {
        return anuradhapura;
    }

    public void setAnuradhapura(double anuradhapura) {
        this.anuradhapura = anuradhapura;
    }

    public double getPolonnaruwa() {
        return polonnaruwa;
    }

    public void setPolonnaruwa(double polonnaruwa) {
        this.polonnaruwa = polonnaruwa;
    }

    public double getMatale() {
        return matale;
    }

    public void setMatale(double matale) {
        this.matale = matale;
    }

    public double getKandy() {
        return kandy;
    }

    public void setKandy(double kandy) {
        this.kandy = kandy;
    }

    public double getNuwaraEliya() {
        return nuwaraEliya;
    }

    public void setNuwaraEliya(double nuwaraEliya) {
        this.nuwaraEliya = nuwaraEliya;
    }

    public double getRatnapura() {
        return ratnapura;
    }

    public void setRatnapura(double ratnapura) {
        this.ratnapura = ratnapura;
    }

    public double getKegalle() {
        return kegalle;
    }

    public void setKegalle(double kegalle) {
        this.kegalle = kegalle;
    }

    public double getTrincomalee() {
        return trincomalee;
    }

    public void setTrincomalee(double trincomalee) {
        this.trincomalee = trincomalee;
    }

    public double getBatticaloa() {
        return batticaloa;
    }

    public void setBatticaloa(double batticaloa) {
        this.batticaloa = batticaloa;
    }

    public double getAmpara() {
        return ampara;
    }

    public void setAmpara(double ampara) {
        this.ampara = ampara;
    }

    public double getMonaragala() {
        return monaragala;
    }

    public void setMonaragala(double monaragala) {
        this.monaragala = monaragala;
    }

    public double getBadulla() {
        return badulla;
    }

    public void setBadulla(double badulla) {
        this.badulla = badulla;
    }
}
