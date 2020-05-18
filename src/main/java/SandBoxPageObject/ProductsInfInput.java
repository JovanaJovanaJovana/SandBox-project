package SandBoxPageObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ProductsInfInput {

	private static String[] columns = { "Name", "ID", "Short Description", "Long Description", "Price", "Tangible",
			"Recurring", "Approved URL" };

	private static List<Products> products = new ArrayList<Products>();

	public static void main(String[] args) throws IOException {

		products.add(new Products("Painting", "01", "Kazimir Malevich - Black Square",
				"The first version"
						+ "was done in 1915. Malevich made four variants of which the last is thought to have "
						+ "been painted during the late 1920s or early 1930s. Black Square was first shown in "
						+ "The Last Futurist Exhibition 0,10 in 1915.",
				"30 000 000$", "Yes", "No", "https://sandbox.2checkout.com/sandbox"));

		products.add(new Products("Painting", "02", "Franz Marc -Blue Horse I",
				"Blaues Pferd, or Blue Horse was created in 1911, the same year that Marc "
						+ "founded The Blue Riders (Der Blaue Reiter).This work, which represents three vividly coloured blue horses looking down in front of a landscape "
						+ "of rolling red hills, is characterized by its bright primary colors and a portrayal that simplicity, and a profound sense of emotion.",
				"18 000 000$", "Yes", "No", "https://sandbox.2checkout.com/sandbox"));

		products.add(new Products("Painting", "03", "Wassily Kandinsky - Composition VII",
				"Kandinsky’s Composition VII is justly considered to be the apex of his artwork before the First Word War. "
						+ "More than 30 sketches made in watercolors and oil paints precede this painting, and they can serve as “documentary” proof of this work creation. "
						+ " The main theme, which is an oval form intersected by an irregular rectangle, is perceived like the center surrounded by the vortex of colors and forms. B"
						+ "y means of records and some works examination art historians defined that the Composition VII is a combination of several themes namely Resurrection, "
						+ "the Judgment Day, the Flood and the Garden of Eden. Such combination is expressed as a symbiosis of pure painting.",
				"20 000 000$", "Yes", "No", "https://sandbox.2checkout.com/sandbox"));

		products.add(new Products("Painting", "04", "Henri Matisse - Blue Nude II",
				"Matisse completed a series of four blue nudes in 1952, each in his favorite pose of entwined legs and raised arm. Matisse "
						+ "had been making cut-outs for eleven years, but had not yet seriously attempted to portray the human figure. In preparation for these works, Matisse filled a notebook with studies. "
						+ "He then created a figure that is abstracted and simplified, a symbol for the nude, before incorporating the nude into his large-scale murals.",
				"25 000 000", "Yes", "No", "https://sandbox.2checkout.com/sandbox"));

		products.add(new Products("Painting", "05", "Paul Gauguin - The Yellow Christ",
				"The Yellow Christ is a symbolic piece that shows the crucifixion of Christ taking place in nineteenth-century northern France as Breton "
						+ "women are gathered in prayer. Gauguin relies heavily on bold lines to define his figures and reserves shading only for the women. The autumn palette of yellow, red and green in the landscape echoes the "
						+ "dominant yellow in the figure of Christ. The bold outlines and flatness of the forms in this painting are typical of the cloisonnist style.",
				"22 000 000", "Yes", "No", "https://sandbox.2checkout.com/sandbox"));

		try (XSSFWorkbook wb = new XSSFWorkbook()) {
			XSSFSheet sheet = wb.createSheet("Products");

			Row header_row = sheet.createRow(0);

			for (int i = 0; i < columns.length; i++) {
				Cell cell = header_row.createCell(i);
				cell.setCellValue(columns[i]);
			}

			int row_number = 1;

			for (Products products : products) {
				Row row = sheet.createRow(row_number++);
				row.createCell(0).setCellValue(products.name);
				row.createCell(1).setCellValue(products.id);
				row.createCell(2).setCellValue(products.short_description);
				row.createCell(3).setCellValue(products.long_description);
				row.createCell(4).setCellValue(products.price);
				row.createCell(5).setCellValue(products.tangible);
				row.createCell(6).setCellValue(products.recurring);
				row.createCell(7).setCellValue(products.approved_url);
			}

			for (int i = 1; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			try (FileOutputStream fo = new FileOutputStream("products.xlsx")) {
				wb.write(fo);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}