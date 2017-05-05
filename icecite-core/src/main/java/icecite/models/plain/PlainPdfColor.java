package icecite.models.plain;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import icecite.models.PdfColor;

// TODO: Allow various color systems, not only RGB.

/**
 * A plain implementation of {@link PdfColor}.
 * 
 * @author Claudius Korzen
 */
public class PlainPdfColor extends PlainPdfResource implements PdfColor {
  /**
   * The RGB value of this color.
   */
  protected float[] rgb;

  /**
   * Creates a new color.
   */
  @AssistedInject
  public PlainPdfColor() {
    this.rgb = new float[3];
  }

  /**
   * Creates a new color.
   * 
   * @param rgb
   *        The RGB value, given by three floats in range [0,255].
   */
  @AssistedInject
  public PlainPdfColor(@Assisted float[] rgb) {
    this.rgb = rgb;
  }

  @Override
  public void setRGB(float[] rgb) {
    this.rgb = rgb;
  }

  @Override
  public void setRGB(float r, float g, float b) {
    this.rgb[0] = r;
    this.rgb[1] = g;
    this.rgb[2] = b;
  }

  @Override
  public float[] getRGB() {
    return this.rgb;
  }
}