package icecite.models.plain;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import icecite.models.PdfCharacter;
import icecite.models.PdfColor;
import icecite.models.PdfFont;
import icecite.models.PdfPage;
import icecite.models.PdfType;

/**
 * A plain implementation of {@link PdfCharacter}.
 * 
 * @author Claudius Korzen
 */
public class PlainPdfCharacter extends PlainPdfElement implements PdfCharacter {
  /**
   * The page in which this character is located.
   */
  protected PdfPage page;

  /**
   * The extraction order number.
   */
  protected int extractionOrderNumber; // TODO: Rename this variable.

  /**
   * The text of this character.
   */
  protected String text;

  /**
   * The font of this character.
   */
  protected PdfFont font;

  /**
   * The font size of this character.
   */
  protected float fontsize;

  /**
   * The color of this character.
   */
  protected PdfColor color;

  // ==========================================================================
  // The constructors.

  /**
   * Creates a new PdfCharacter.
   * 
   * @param page
   *        The page in which this character is located.
   */
  @AssistedInject
  public PlainPdfCharacter(@Assisted PdfPage page) {
    this.page = page;
  }

  // ==========================================================================

  @Override
  public PdfPage getPage() {
    return this.page;
  }

  @Override
  public void setPage(PdfPage page) {
    this.page = page;
  }

  // ==========================================================================

  @Override
  public PdfColor getColor() {
    return this.color;
  }

  @Override
  public void setColor(PdfColor color) {
    this.color = color;
  }

  // ==========================================================================

  @Override
  public PdfFont getFont() {
    return this.font;
  }

  @Override
  public void setFont(PdfFont font) {
    this.font = font;
  }

  // ==========================================================================

  @Override
  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String getText() {
    return this.text;
  }

  // ==========================================================================

  @Override
  public void setFontSize(float fontsize) {
    this.fontsize = fontsize;
  }

  @Override
  public float getFontSize() {
    return this.fontsize;
  }

  // ==========================================================================

  @Override
  public void setExtractionOrderNumber(int num) { // TODO: Rename this method.
    this.extractionOrderNumber = num;
  }

  @Override
  public int getExtractionOrderNumber() {
    return this.extractionOrderNumber;
  }

  // ==========================================================================

  @Override
  public PdfType getType() {
    return PdfType.CHARACTERS;
  }

  // ==========================================================================

  @Override
  public String toString() {
    return "PlainPdfCharacter(" + this.text + ", " + this.boundingBox + ")";
  }
}
