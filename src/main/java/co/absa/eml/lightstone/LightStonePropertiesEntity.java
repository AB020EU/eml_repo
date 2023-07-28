package co.absa.eml.lightstone;



import co.absa.eml.utilities.Auditable;
import co.absa.eml.utilities.Auditable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LightStonePropertiesEntity extends Auditable<String> {

    @Id
    String id;

    String prop_id;
    String deed_id;
    String proptype_id;
    String ss_id;
    String nad_id;
    String property_type;
    String province;
    String municname;
    String deedtown;
    String farmname;
    String sectional_title;
    String unit;
    String erf;
    String portion;
    String buyer_name;
    String firstname;
    String MIDDLENAME;
    String MIDDLENAME2;
    String MIDDLENAME3;
    String surname;
    String person_type_id;
    String buyer_idck;
    String munic_id;
    String prov_id;
    String street_number;
    String street_name;
    String street_type;
    String po_code;
    String garage;
    String nadmunic;
    String size;
    String sub_id;
    String type_id;
    String street_id;
    String sect_scheme_no;
    String minunit;
    String maxunit;
    String township;
    String erf_key;
    String sg_code;
    String registrar;
    String province_full;
    String estate_name;
    String re;
    String add_description;
    String suburb;
    String nadsuburb;
    String est_id;
    String x;
    String y;
    String property_type_full;
    String deeds_size;
    String archived;
    String active_status;
    String title_deed;
    String distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProp_id() {
        return prop_id;
    }

    public void setProp_id(String prop_id) {
        this.prop_id = prop_id;
    }

    public String getDeed_id() {
        return deed_id;
    }

    public void setDeed_id(String deed_id) {
        this.deed_id = deed_id;
    }

    public String getProptype_id() {
        return proptype_id;
    }

    public void setProptype_id(String proptype_id) {
        this.proptype_id = proptype_id;
    }

    public String getSs_id() {
        return ss_id;
    }

    public void setSs_id(String ss_id) {
        this.ss_id = ss_id;
    }

    public String getNad_id() {
        return nad_id;
    }

    public void setNad_id(String nad_id) {
        this.nad_id = nad_id;
    }

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMunicname() {
        return municname;
    }

    public void setMunicname(String municname) {
        this.municname = municname;
    }

    public String getDeedtown() {
        return deedtown;
    }

    public void setDeedtown(String deedtown) {
        this.deedtown = deedtown;
    }

    public String getFarmname() {
        return farmname;
    }

    public void setFarmname(String farmname) {
        this.farmname = farmname;
    }

    public String getSectional_title() {
        return sectional_title;
    }

    public void setSectional_title(String sectional_title) {
        this.sectional_title = sectional_title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getErf() {
        return erf;
    }

    public void setErf(String erf) {
        this.erf = erf;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMIDDLENAME() {
        return MIDDLENAME;
    }

    public void setMIDDLENAME(String MIDDLENAME) {
        this.MIDDLENAME = MIDDLENAME;
    }

    public String getMIDDLENAME2() {
        return MIDDLENAME2;
    }

    public void setMIDDLENAME2(String MIDDLENAME2) {
        this.MIDDLENAME2 = MIDDLENAME2;
    }

    public String getMIDDLENAME3() {
        return MIDDLENAME3;
    }

    public void setMIDDLENAME3(String MIDDLENAME3) {
        this.MIDDLENAME3 = MIDDLENAME3;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPerson_type_id() {
        return person_type_id;
    }

    public void setPerson_type_id(String person_type_id) {
        this.person_type_id = person_type_id;
    }

    public String getBuyer_idck() {
        return buyer_idck;
    }

    public void setBuyer_idck(String buyer_idck) {
        this.buyer_idck = buyer_idck;
    }

    public String getMunic_id() {
        return munic_id;
    }

    public void setMunic_id(String munic_id) {
        this.munic_id = munic_id;
    }

    public String getProv_id() {
        return prov_id;
    }

    public void setProv_id(String prov_id) {
        this.prov_id = prov_id;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getStreet_type() {
        return street_type;
    }

    public void setStreet_type(String street_type) {
        this.street_type = street_type;
    }

    public String getPo_code() {
        return po_code;
    }

    public void setPo_code(String po_code) {
        this.po_code = po_code;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public String getNadmunic() {
        return nadmunic;
    }

    public void setNadmunic(String nadmunic) {
        this.nadmunic = nadmunic;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getStreet_id() {
        return street_id;
    }

    public void setStreet_id(String street_id) {
        this.street_id = street_id;
    }

    public String getSect_scheme_no() {
        return sect_scheme_no;
    }

    public void setSect_scheme_no(String sect_scheme_no) {
        this.sect_scheme_no = sect_scheme_no;
    }

    public String getMinunit() {
        return minunit;
    }

    public void setMinunit(String minunit) {
        this.minunit = minunit;
    }

    public String getMaxunit() {
        return maxunit;
    }

    public void setMaxunit(String maxunit) {
        this.maxunit = maxunit;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getErf_key() {
        return erf_key;
    }

    public void setErf_key(String erf_key) {
        this.erf_key = erf_key;
    }

    public String getSg_code() {
        return sg_code;
    }

    public void setSg_code(String sg_code) {
        this.sg_code = sg_code;
    }

    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    public String getProvince_full() {
        return province_full;
    }

    public void setProvince_full(String province_full) {
        this.province_full = province_full;
    }

    public String getEstate_name() {
        return estate_name;
    }

    public void setEstate_name(String estate_name) {
        this.estate_name = estate_name;
    }

    public String getRe() {
        return re;
    }

    public void setRe(String re) {
        this.re = re;
    }

    public String getAdd_description() {
        return add_description;
    }

    public void setAdd_description(String add_description) {
        this.add_description = add_description;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getNadsuburb() {
        return nadsuburb;
    }

    public void setNadsuburb(String nadsuburb) {
        this.nadsuburb = nadsuburb;
    }

    public String getEst_id() {
        return est_id;
    }

    public void setEst_id(String est_id) {
        this.est_id = est_id;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getProperty_type_full() {
        return property_type_full;
    }

    public void setProperty_type_full(String property_type_full) {
        this.property_type_full = property_type_full;
    }

    public String getDeeds_size() {
        return deeds_size;
    }

    public void setDeeds_size(String deeds_size) {
        this.deeds_size = deeds_size;
    }

    public String getArchived() {
        return archived;
    }

    public void setArchived(String archived) {
        this.archived = archived;
    }

    public String getActive_status() {
        return active_status;
    }

    public void setActive_status(String active_status) {
        this.active_status = active_status;
    }

    public String getTitle_deed() {
        return title_deed;
    }

    public void setTitle_deed(String title_deed) {
        this.title_deed = title_deed;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
