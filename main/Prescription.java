package main;
import java.util.*;

public class Prescription {
	
	private ArrayList<MedicineBlock> medicineBlock = new ArrayList<MedicineBlock>(); 
	MedicineBlock mb = new MedicineBlock();
	
	public Prescription() {}
	
	public Prescription(MedicineBlock mb){
		this.medicineBlock.add(mb); 
	}
	
	public void addInPrescription(MedicineBlock mb) {
		medicineBlock.add(mb);
	}
	
	public void printPrescription() {
		if(medicineBlock.size()>0) {
			System.out.println();
			for(int i=0;i<30;i++)
				System.out.print('-');
			for(MedicineBlock b: medicineBlock) 
				b.printMedicineBlock();
		}
		
	}
	
	public MedicineBlock retMedicineBlock() {
		return this.medicineBlock.get(medicineBlock.size()-1);
	}
	
}
