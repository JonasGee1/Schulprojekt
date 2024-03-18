package Code.Backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Veranstaltungsliste {
    private ArrayList<Company> companies = new ArrayList<>();
    public void addCompany(Company company){
        this.companies.add(company);
    }

    public ArrayList<Company> getCompanies(){
        return this.companies;
    }

    public void printList(){
        for(Company c : this.companies){
            System.out.println(c.getString());
        }
    }

    public void sortCompaniesBySize() {
        // Verwenden eines Comparator, um die Unternehmen nach Größe zu vergleichen
        Comparator<Company> comparator = new Comparator<Company>() {
            @Override
            public int compare(Company c1, Company c2) {
                // Vergleich der Anzahl von Schülern in den Unternehmen
                return Integer.compare(c2.getCount(), c1.getCount());
            }
        };

        // Sortieren der Unternehmen unter Verwendung des Comparators
        Collections.sort(companies, comparator);
    }
}
