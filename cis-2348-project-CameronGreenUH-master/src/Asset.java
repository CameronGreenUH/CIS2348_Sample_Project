/*Declaring multiple classes within the same file

Super class*/
public class Asset {

    private String assetIdNumber;
    private String assetName;
    private String assetType;
    private String assetLocation;
    private String usedBy;
    private String assetState;

    // default constructor
    public Asset(){
    }

    public Asset(String assetIdNumber, String assetName, String assetType, String assetLocation, String usedBy, String assetState){
        this.setAssetIdNumber(assetIdNumber);
        this.setAssetName(assetName);
        this.setAssetType(assetType);
        this.setAssetLocation(assetLocation);
        this.setUsedBy(usedBy);
        this.setAssetState(assetState);
    }

    public String getAssetIdNumber() {
        return assetIdNumber;
    }

    public String setAssetIdNumber(String assetIdNumber) {
        this.assetIdNumber = assetIdNumber;
        return assetIdNumber;
    }

    public String getAssetName() {
        return assetName;
    }

    public String setAssetName(String assetName) {
        this.assetName = assetName;
        return assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public String setAssetType(String assetType) {
        this.assetType = assetType;
        return assetType;
    }

    public String getAssetLocation() {
        return assetLocation;
    }

    public String setAssetLocation(String assetLocation) {
        this.assetLocation = assetLocation;
        return assetLocation;
    }

    public String getAssetState() {
        return assetState;
    }

    public String setAssetState(String assetState) {
        this.assetState = assetState;
        return assetState;
    }

    public String getUsedBy() {
        return usedBy;
    }

    public String setUsedBy(String usedBy) {
        this.usedBy = usedBy;
        return usedBy;
    }

}

// sub class
class Computer extends Asset implements ComputerInterface {
    // constructor
    public Computer(String assetIdNumber, String assetName, String assetType, String assetLocation, String usedBy, String assetState){

        super.setAssetIdNumber(assetIdNumber);
        super.setAssetName(assetName);
        super.setAssetType(assetType);
        super.setAssetLocation(assetLocation);
        super.setUsedBy(usedBy);
        super.setAssetState(assetState);
    }

    // determines if the computer is a laptop or desktop based on random value
    public static void computerType(){
        long value = Math.round(Math.random() * 10);

        if (value % 2 == 0){
            System.out.println("The device is a laptop.");
        } else if (value % 2 == 1){
            System.out.println("The device is a desktop");
        }
        else{
            System.out.println("Error, could not determine computer type");
        }
    }

    // interface implementation
    @Override
    public boolean checkForSoftwareUpdate() {
        return false;
    }

    @Override
    public void getIPAddressFromServer() {

    }
}

// sub class
class Printer extends Asset implements PrinterInterface {

    // constructor
    public Printer(String assetIdNumber, String assetName, String assetType, String assetLocation, String usedBy, String assetState) {
        super.setAssetIdNumber(assetIdNumber);
        super.setAssetName(assetName);
        super.setAssetType(assetType);
        super.setAssetLocation(assetLocation);
        super.setUsedBy(usedBy);
        super.setAssetState(assetState);;
    }

    // determines what color the printer will use to print
    public static void printColor(){
        long r = Math.round(Math.random() * 256);
        long g = Math.round(Math.random() * 256);
        long b = Math.round(Math.random() * 256);

        System.out.println("The RGB values for the color to be printed are: (" + r + ", " + g + ", " + b + ")");
    }

    // interface implementation
    @Override
    public boolean checkForMaintenance() {
        return false;
    }

    @Override
    public void getIPAddressFromPool() {

    }
}

// sub class
class AV extends Asset implements AVInterface {
    // constructor
    public AV (String assetIdNumber, String assetName, String assetType, String assetLocation, String usedBy, String assetState){
        super.setAssetIdNumber(assetIdNumber);
        super.setAssetName(assetName);
        super.setAssetType(assetType);
        super.setAssetLocation(assetLocation);
        super.setUsedBy(usedBy);
        super.setAssetState(assetState);
    }

    // determines whether equipment is audio or video equipment
    public static void equipmentType(){
        long value = Math.round(Math.random() * 10);

        if (value % 2 == 0){
            System.out.println("The device is audio equipment.");
        } else if (value % 2 == 1){
            System.out.println("The device is video equipment");
        }
        else{
            System.out.println("Error, could not determine equipment type");
        }
    }

    // interface implementation
    @Override
    public boolean checkForNeedCleaning() {
        return false;
    }

    @Override
    public void setIPAddress() {

    }
}
