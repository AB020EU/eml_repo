package co.absa.eml.soupinteractionpoint;

import co.absa.eml.applicationcapture.ApplicationCaptureData;
import co.absa.eml.dto.CaptureApplication;
import co.absa.eml.restinteractionpoints.RestInteractionPoints;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class SoapEnvelope {

    public String XML(CaptureApplication captureApplication) {
        RestInteractionPoints restInteractionPoints = new RestInteractionPoints();

        Response response0 = restInteractionPoints.get("/get/application/data");
       //TODO:date of birth
        //TODO: name and surname


        String a = "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <SOAP-ENV:Header/>\n" +
                "   <S:Body>\n" +
                "      <ns2:submitApplicationRequest xmlns:ns2=\"http://v3.fulfilment.service.homeloan.absa.co.za/HomeLoanFulfilmentSOAP/\">\n" +
                "         <applicationDetails>\n" +
                "            <applicationNo\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <corpCode>ABS</corpCode>\n" +
                "            <userName>SwitchX</userName>\n" +
                "            <purposeOfApplication>O</purposeOfApplication>\n" +
                "            <creditCheckAgreement>Y</creditCheckAgreement>\n" +
                "            <singleHousehold>Y</singleHousehold>\n" +
                "            <surety>N</surety>\n" +
                "            <existingAbsaHLAccount\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <typeOfApplication>001</typeOfApplication>\n" +
                "            <applicant>N</applicant>\n" +
                "            <preferredBranch>8392</preferredBranch>\n" +
                "            <reApplicationInd>N</reApplicationInd>\n" +
                "            <previousApplicationNo\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <debtConsolidationIndicator>N</debtConsolidationIndicator>\n" +
                "            <debtConsolidationOption>02</debtConsolidationOption>\n" +
                "            <insuranceQuoteInd>N</insuranceQuoteInd>\n" +
                "            <lifeInsuranceInd>N</lifeInsuranceInd>\n" +
                "            <willInd>N</willInd>\n" +
                "            <notificationDetails>\n" +
                "               <notificationMethod>E</notificationMethod>\n" +
                "               <cellPhoneNumber\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <emailAddress>"+captureApplication.getEmail()+"</emailAddress>\n" +
                "            </notificationDetails>\n" +
                "            <originatorDetails>\n" +
                "               <originatorName\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <originatorType>BO</originatorType>\n" +
                "               <originatorCode>INDBOND024</originatorCode>\n" +
                "               <consultantName\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <referenceNo\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <originatorIndicator>Y</originatorIndicator>\n" +
                "               <prefCommChannel>E</prefCommChannel>\n" +
                "               <telephoneCode\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <telephoneNumber\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <cellPhoneNumber\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <emailAddress>"+captureApplication.getEmail()+"</emailAddress>\n" +
                "            </originatorDetails>\n" +
                "            <contractorDetails\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <valuationDetails>\n" +
                "               <valuationContactName>contact name</valuationContactName>\n" +
                "               <valuationTelCode>011</valuationTelCode>\n" +
                "               <valuationTelNumber>4561235</valuationTelNumber>\n" +
                "               <valuationCellNumber\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <valuationAltContactDetails\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            </valuationDetails>\n" +
                "            <attorneyDetails>\n" +
                "               <transferAttorney>DE VILLIERS DE BEER</transferAttorney>\n" +
                "               <attorneyTelNumber>0123208111</attorneyTelNumber>\n" +
                "            </attorneyDetails>\n" +
                "         </applicationDetails>\n" +
                "         <personalDetails>\n" +
                "            <title>05</title>\n" +
                "            <fullNames>STEPHEN CHARL</fullNames>\n" +
                "            <surname>THORNTON</surname>\n" +
                "            <identificationType>01</identificationType>\n" +
                "            <otherIdentificationType\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <idPassportNumber>"+response0.body().path("id_number")+"</idPassportNumber>\n" +
                "            <dateOfBirth>19770708</dateOfBirth>\n" +
                "            <gender>1</gender>\n" +
                "            <race>W</race>\n" +
                "            <otherRaceDescription\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <countryOfBirth>SO003</countryOfBirth>\n" +
                "            <countryOfPassportIssue\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <saPermanentResidency\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <saEmployed\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <nationality>SOU01</nationality>\n" +
                "            <tempResidentInd>N</tempResidentInd>\n" +
                "            <tempResidentPermitNumber\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <maritalStatusCode>1</maritalStatusCode>\n" +
                "            <maritalContractCode>2</maritalContractCode>\n" +
                "            <registerSpouse>N</registerSpouse>\n" +
                "            <spouseIncomeRequired>N</spouseIncomeRequired>\n" +
                "            <postMatricIndicator>Y</postMatricIndicator>\n" +
                "            <postMatricQualification>5</postMatricQualification>\n" +
                "            <languageCorrespondence>E</languageCorrespondence>\n" +
                "            <languageHome>E</languageHome>\n" +
                "            <typeOfParticipantCode>P</typeOfParticipantCode>\n" +
                "            <participantIDPassport\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <contactDetails>\n" +
                "               <physicalAddressCountry>SO003</physicalAddressCountry>\n" +
                "               <residentialAddressLine1>223 Telco Street</residentialAddressLine1>\n" +
                "               <residentialAddressLine2\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <residentialSuburb>"+response0.body().path("")+"</residentialSuburb>\n" +
                "               <residentialCity>CAPE TOWN</residentialCity>\n" +
                "               <residentialPostalCode>7550</residentialPostalCode>\n" +
                "               <postalAddressLine1>"+response0.body().path("street_number")+""+response0.body().path("street_name")+"</postalAddressLine1>\n" +
                "               <postalSuburb>"+response0.body().path("suburb")+"</postalSuburb>\n" +
                "               <postalCity>CAPE TOWN</postalCity>\n" +
                "               <postalCode>7551</postalCode>\n" +
                "               <presentAddressSince>20050513</presentAddressSince>\n" +
                "               <residentialStatus>B</residentialStatus>\n" +
                "               <homeTelephoneCode></homeTelephoneCode>\n" +
                "               <homeTelephoneNumber></homeTelephoneNumber>\n" +
                "               <cellPhoneNumber>0824990687</cellPhoneNumber>\n" +
                "               <emailAddress>"+captureApplication.getEmail()+"</emailAddress>\n" +
                "               <communicationMethod>E</communicationMethod>\n" +
                "            </contactDetails>\n" +
                "            <employmentDetails>\n" +
                "               <employStatus>01</employStatus>\n" +
                "               <employmentSector>05</employmentSector>\n" +
                "               <absaStaff>N</absaStaff>\n" +
                "               <employerName>Abc Corporation Ltd</employerName>\n" +
                "               <employerAddressCountry>SO003</employerAddressCountry>\n" +
                "               <employerAddrLine1>555 Stanza Bopape Street</employerAddrLine1>\n" +
                "               <employerSuburb>MONTANA</employerSuburb>\n" +
                "               <employerCity>PRETORIA</employerCity>\n" +
                "               <employerPostalCode>0812</employerPostalCode>\n" +
                "               <absaEmployeeNumber\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <workTelephoneCode></workTelephoneCode>\n" +
                "               <workTelephoneNumber></workTelephoneNumber>\n" +
                "               <employedSince>20180813</employedSince>\n" +
                "               <occupationCode>15</occupationCode>\n" +
                "               <sourceOfIncome>20</sourceOfIncome>\n" +
                "               <socialGrant>N</socialGrant>\n" +
                "               <housingScheme>N</housingScheme>\n" +
                "            </employmentDetails>\n" +
                "            <financialDetails>\n" +
                "               <grossMonthIncome>150500</grossMonthIncome>\n" +
                "               <rentalIncome>0</rentalIncome>\n" +
                "               <futureRentalIncome\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <commission>0</commission>\n" +
                "               <overtime>0</overtime>\n" +
                "               <maintenance>0</maintenance>\n" +
                "               <otherIncome>0</otherIncome>\n" +
                "               <otherIncomeDescription>Other</otherIncomeDescription>\n" +
                "               <deductMedicalAid>500</deductMedicalAid>\n" +
                "               <deductPension>500</deductPension>\n" +
                "               <deductTax>8000</deductTax>\n" +
                "               <deductUIF>148</deductUIF>\n" +
                "               <otherDeduction>700</otherDeduction>\n" +
                "               <otherDeductionDescription>OtherdeductionDescri</otherDeductionDescription>\n" +
                "               <currentRentBond>5000</currentRentBond>\n" +
                "               <currentRentBondCancelled>5000</currentRentBondCancelled>\n" +
                "               <loanAndOverdraft>0</loanAndOverdraft>\n" +
                "               <creditCardAccount>5000</creditCardAccount>\n" +
                "               <retailAccount>3500</retailAccount>\n" +
                "               <assetFinanceAndLeaseRepayment>0</assetFinanceAndLeaseRepayment>\n" +
                "               <otherDebt>0</otherDebt>\n" +
                "               <debtConsolidation\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <maintenancePayment>0</maintenancePayment>\n" +
                "               <groceries>13000</groceries>\n" +
                "               <utility>2500</utility>\n" +
                "               <domesticWages>0</domesticWages>\n" +
                "               <telephoneBill>450</telephoneBill>\n" +
                "               <entertainment>0</entertainment>\n" +
                "               <security>250</security>\n" +
                "               <totalMonthlyRent>0</totalMonthlyRent>\n" +
                "               <rentAmountToStop>0</rentAmountToStop>\n" +
                "               <educationalFees>2000</educationalFees>\n" +
                "               <transportOrPetrolCost>1000</transportOrPetrolCost>\n" +
                "               <insuranceAndFuneralCost>0</insuranceAndFuneralCost>\n" +
                "               <otherExpenses>4150</otherExpenses>\n" +
                "               <otherExpensesDescription>Other</otherExpensesDescription>\n" +
                "               <chequeAccount>N</chequeAccount>\n" +
                "               <chequeAccNumber\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <chequeAccInstitution\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <creditCard>Y</creditCard>\n" +
                "               <hlToBeSettled>N</hlToBeSettled>\n" +
                "               <hlAccInstitution\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <hlAccNumber\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <hlAccCreditBalance\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <insolvent>N</insolvent>\n" +
                "               <rehabilitateDate\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <debtCounselling>N</debtCounselling>\n" +
                "               <debtCouncellorName\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <debtCouncellorNumber\n" +
                "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "               <idxAccountDetails>\n" +
                "                  <idxAccountType>CH</idxAccountType>\n" +
                "                  <idxInstitution>ST</idxInstitution>\n" +
                "                  <idxAccountNumber>6201234567</idxAccountNumber>\n" +
                "                  <idxBranchCode>51001</idxBranchCode>\n" +
                "               </idxAccountDetails>\n" +
                "               <deaConsent>Y</deaConsent>\n" +
                "            </financialDetails>\n" +
                "         </personalDetails>\n" +
                "         <productDetails>\n" +
                "            <applicationNo\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <purchasePrice>1900000</purchasePrice>\n" +
                "            <contractPrice\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <marketValue\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <amountFurtherLoan\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <totalLoanRequired>900000</totalLoanRequired>\n" +
                "            <bondRegistrationAmt>1900000</bondRegistrationAmt>\n" +
                "            <sourceOfFunds>20</sourceOfFunds>\n" +
                "            <purchaseDate>20221013</purchaseDate>\n" +
                "            <loanTermYear>20</loanTermYear>\n" +
                "            <preferredRepaymentDay>1</preferredRepaymentDay>\n" +
                "            <familySpringboardInd>N</familySpringboardInd>\n" +
                "            <loyaltyHomeInd>N</loyaltyHomeInd>\n" +
                "            <myHomeInd>N</myHomeInd>\n" +
                "            <multiPlanIndicator>N</multiPlanIndicator>\n" +
                "         </productDetails>\n" +
                "         <propertyDetails>\n" +
                "            <applicationNo\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <propertyType>D</propertyType>\n" +
                "            <streetName>Telco Street</streetName>\n" +
                "            <streetNumber>1234</streetNumber>\n" +
                "            <erfNumber>"+response0.body().path("erf")+"</erfNumber>\n" +
                "            <portionNumber\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <complexName\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <suburb>DOORNPOORT</suburb>\n" +
                "            <township>DOORNPOORT</township>\n" +
                "            <city>PRETORIA</city>\n" +
                "            <unitNumber\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <flatNumber\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "            <occupationBy>S</occupationBy>\n" +
                "            <wallType>FB</wallType>\n" +
                "            <roofType>CY</roofType>\n" +
                "            <thatchRoof>N</thatchRoof>\n" +
                "            <primaryResidenceIndicator>N</primaryResidenceIndicator>\n" +
                "            <sellerIdType>03</sellerIdType>\n" +
                "            <sellerIdPassport>123456Test</sellerIdPassport>\n" +
                "            <newDevelopmentIndicator>N</newDevelopmentIndicator>\n" +
                "            <newDevelopmentID\n" +
                "               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\n" +
                "         </propertyDetails>\n" +
                "      </ns2:submitApplicationRequest>\n" +
                "   </S:Body>\n" +
                "</S:Envelope>\n";
        return a;
    }
}
