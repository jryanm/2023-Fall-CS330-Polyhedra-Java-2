package polyhedra;

import java.util.Scanner;
import java.util.List;
import java.util.Vector;
import java.util.Iterator;

/**
 * A Composite Polyhedron. Polyhedra of this type are built from
 * other Polyhedra. This,in theory, can include Composite objects
 * composed of other (nested) Composite objects.
 *
 * @author REPLACE_THIS_WITH_YOUR_NAME
 */
public class Composite extends Polyhedron
    implements Cloneable, Iterable<Polyhedron>
{

    /**
     * Collection of polyhedra of which this composite polyhedron is composed.
     */
    private List<Polyhedron> allPolyhedra;

    /**
     * Default Composite Constructor.
     */
    public Composite()
    {
        super("Composite");

        allPolyhedra = new Vector<Polyhedron>();
    }

    /**
     * Composite Copy Constructor.
     *
     * @param src source Composite object to copy
     *
     * @TODO complete this function
     * --> Done!
     */
    public Composite(Composite src)
    {
        // Name the composite and create a new vector to store polyhedra
        super("Composite");
        allPolyhedra = new Vector<Polyhedron>();

        // Add each polyhedron from src polyhedra vector to allPolyhedra
        for(Polyhedron poly : src.allPolyhedra){
            this.allPolyhedra.add(poly.clone());
            this.boundingBox.merge(poly.getBoundingBox());
        }
    }

    /**
     * Add a cloned copy of a Polyhedron to the `Composite` collection.
     *
     * @param toAdd is cloned and the copy is added
     *
     * @TODO complete this function
     * --> Done!
     */
    public void add(Polyhedron toAdd)
    {
        // Clone toAdd and add it to allPolyhedra
        // --> After adding polyhedron, merge the boundingBoxes
        this.allPolyhedra.add(toAdd.clone());
        this.boundingBox.merge(toAdd.getBoundingBox());
    }

    /**
     * Read all component polyhedra.
     *
     * @param scanner input source
     *
     * @TODO complete this function
     * --> Done!
     */
    public void read(Scanner scanner)
    {
        // Read number of polyhedra to add to vector
        int numPoylhedra = scanner.nextInt();

        // Read in, create, and add polyhedron to vector
        // --> After polyhedron is added, merge the boundingBoxes
        for(int i = 0; i < numPoylhedra; ++i){
            Polyhedron poly = PolyhedronFactory.createAndRead(scanner);
            
            this.allPolyhedra.add(poly);
            this.boundingBox.merge(poly.getBoundingBox());
        }
    }

    /**
     * Iterate through all sub-polyhedra, scale them, and update all
     * bounding boxes.
     *
     * @param scalingFactor scaling factor
     *
     * @TODO complete this function
     */
    public void scale(double scalingFactor)
    {
        // Scale all polyhedra in vector
        for(Polyhedron poly : this.allPolyhedra){
            poly.scale(scalingFactor);
        }

        // Scale boundingBox
        this.boundingBox.scale(scalingFactor);
    }

    /**
     * Retrive the number of Polyhedra.
     *
     * @return the number of Polyhedra that comprise this Composite object
     */
    public int size()
    {
        return allPolyhedra.size();
    }

    @Override
    public Iterator<Polyhedron> iterator()
    {
        return allPolyhedra.iterator();
    }

    @Override
    public Polyhedron clone()
    {
        return new Composite(this);
    }

    /**
     * "Print" all polyhedra.
     *
     * @return String containing all component _Polyhedra_ objects
     *
     * @TODO complete this function
     * --> Done!
     */
    @Override
    public String toString()
    {
        // StringBuilder: Allows String construction
        StringBuilder compositeInfo = new StringBuilder();

        // Line 1: Output Composite Info
        compositeInfo.append(
            String.format("%s%d polyhedra\n", super.toString(), this.size())
        );

        // Lines 2+: Output info for each shape in allPolyhedra (indented)
        for(Polyhedron poly : this.allPolyhedra){
            compositeInfo.append(
                String.format("  %s\n", poly.toString())
            );
        }
        
        // Return: Everything added to StringBuilder formatted as String
        return compositeInfo.toString();
    }
}

