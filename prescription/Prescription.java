package prescription;
import java.io.Serializable;
import java.util.*;


public class Prescription implements Serializable{
	
	private ArrayList<MedicineBlock> medicineBlock = new ArrayList<MedicineBlock>(); 
	MedicineBlock mb = new MedicineBlock();
	
	public Prescription() {}
	
	public Prescription(MedicineBlock mb){
		this.medicineBlock.add(mb); 
	}
	
	public void addInPrescription(MedicineBlock mb) {
		medicineBlock.add(mb);
	}
	
	public String printPrescription() {
		String returnValue="";
		if(medicineBlock.size()>0) {
			returnValue+="\n";
			for(int i=0;i<20;i++)
				returnValue+="-";
			for(MedicineBlock b: medicineBlock) 
				returnValue+=b.printMedicineBlock();
		}
		return returnValue;
	}
	
	public MedicineBlock retMedicineBlock() {
		return this.medicineBlock.get(medicineBlock.size()-1);
	}
	
}
