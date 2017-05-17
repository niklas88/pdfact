package icecite.tokenizer;

import java.util.List;

import com.google.inject.Inject;

import icecite.models.PdfCharacterSet;
import icecite.models.PdfCharacterSet.PdfCharacterSetFactory;
import icecite.models.PdfDocument;
import icecite.models.PdfPage;
import icecite.models.PdfWord;
import icecite.models.PdfWord.PdfWordFactory;
import icecite.tokenizer.xycut.XYCut;
import icecite.utils.geometric.plain.PlainRectangle;

/**
 * An implementation of {@link PdfWordTokenizer} based on XYCut.
 * 
 * @author Claudius Korzen
 */
public class XYCutPdfWordTokenizer extends XYCut<PdfWord>
    implements PdfWordTokenizer {
  /**
   * The factory to create instances of PdfWord.
   */
  protected PdfWordFactory wordFactory;

  // ==========================================================================
  // Constructors.

  /**
   * Creates a new word tokenizer.
   * 
   * @param characterSetFactory
   *        The factory to create instances of {@link PdfCharacterSet} (needed
   *        for XYCut).
   * @param wordFactory
   *        The factory to create instance of {@link PdfWord}.
   */
  @Inject
  public XYCutPdfWordTokenizer(PdfCharacterSetFactory characterSetFactory,
      PdfWordFactory wordFactory) {
    super(characterSetFactory);
    this.wordFactory = wordFactory;
  }

  // ==========================================================================

  @Override
  public List<PdfWord> tokenize(PdfDocument pdf, PdfPage page,
      PdfCharacterSet characters) {
    return cut(pdf, page, characters);
  }

  // ==========================================================================

  @Override
  public float getVerticalLaneWidth(PdfDocument pdf, PdfPage page,
      PdfCharacterSet characters) {
    return .1f;
    // return 0.5f * estimateWhitespaceWidth(area, 1f, Float.MAX_VALUE);
  }

  @Override
  public boolean isValidVerticalLane(PdfDocument pdf, PdfPage page,
      PdfCharacterSet left, PdfCharacterSet overlap, PdfCharacterSet right) {
    return overlap.isEmpty();
  }

  // ==========================================================================

  @Override
  public float getHorizontalLaneHeight(PdfDocument pdf, PdfPage page,
      PdfCharacterSet characters) {
    return Float.MAX_VALUE;
  }

  @Override
  public boolean isValidHorizontalLane(PdfDocument pdf, PdfPage page,
      PdfCharacterSet upper, PdfCharacterSet overlap, PdfCharacterSet lower) {
    return false;
  }

  // ==========================================================================

  @Override
  public PdfWord pack(PdfPage page, PdfCharacterSet characters) {
    // FIXME
    PdfWord word = this.wordFactory.create(characters);
    // TODO: Use guice here.
    word.setBoundingBox(PlainRectangle.fromBoundingBoxOf(characters));
    word.setPage(page);
    return word;
  }
  
  // ==========================================================================
  
//  /**
//   * Estimates the width of whitespace in the given area. If this distance is 
//   * smaller than the given minValue, the value of minValue is returned. 
//   * If there is no proper whitespace was found, the given default value is
//   * returned.
//   */
//  public float estimateWhitespaceWidth(PdfArea area, float minValue, 
//      float defaultValue) {
//    List<PdfCharacter> chars = area.getTextCharacters();
//    
//    // Sort the characters of area by minX values to be able to obtain the
//    // distance of a character to its previous and next character.
//    Collections.sort(chars, new MinXComparator());
//        
//    FloatCounter distanceCounter = new FloatCounter();
//    
//    // Iterate through each character and compute its distance to prev and 
  // next.
//    for (int i = 0; i < chars.size(); i++) {
//      PdfCharacter prev = i > 0 ? chars.get(i - 1) : null;
//      PdfCharacter curr = chars.get(i);
//      PdfCharacter next = i < chars.size() - 1 ? chars.get(i + 1) : null;
//      
//      if (prev != null && curr != null && next != null) {
//        Rectangle prevRect = prev.getBoundingBox();
//        Rectangle rect = curr.getBoundingBox();
//        Rectangle nextRect = next.getBoundingBox();
//        
//        // Compute distance between prev and curr.
//        float left = MathUtils.floor(rect.getMinX() - prevRect.getMaxX(), 1);
//        // Consider negative distances as "0". 
//        left = Math.max(left, 0);
//        
//        // Compute distance between curr and next.
//        float right = MathUtils.floor(nextRect.getMinX() - rect.getMaxX(), 1);
//        // Consider negative distances as "0".
//        right = Math.max(right, 0);
//        
//        // If one of the distances is larger than the other, register it in 
  // the
//        // counter.
//        if (MathUtils.isLarger(left, right, 1f)) {
//          distanceCounter.add(left);
//        }
//        
//        if (MathUtils.isLarger(right, left, 1f)) {
//          distanceCounter.add(right);
//        }
//      }
//    }
//    
//    // If there was at least one whitespace found, return the most frequent 
//    // width, otherwise return the default value.
//    if (distanceCounter.size() > 0) {
//      return Math.max(distanceCounter.getMostFrequentFloat(), minValue);
//    } else {
//      return defaultValue;
//    }
//  }
}