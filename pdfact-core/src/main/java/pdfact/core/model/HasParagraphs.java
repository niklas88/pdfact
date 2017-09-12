package pdfact.core.model;

import java.util.List;

/**
 * An interface that is implemented by PDF elements that have paragraphs.
 *
 * @author Claudius Korzen
 */
public interface HasParagraphs extends HasTextLineStatistic,
    HasCharacterStatistic {
  /**
   * Returns the paragraphs of this element.
   * 
   * @return The paragraphs of this element.
   */
  List<Paragraph> getParagraphs();

  /**
   * Returns the first paragraph of this element.
   * 
   * @return The first paragraph or null if there are no paragraphs.
   */
  Paragraph getFirstParagraph();

  /**
   * Returns the last paragraph of this element.
   * 
   * @return The last paragraph or null if there are no paragraphs.
   */
  Paragraph getLastParagraph();

  // ==========================================================================

  /**
   * Sets the paragraphs of this element.
   * 
   * @param paragraphs
   *        The paragraphs of this element.
   */
  void setParagraphs(List<Paragraph> paragraphs);

  /**
   * Adds the given paragraphs to this element.
   * 
   * @param paragraphs
   *        The paragraphs to add.
   */
  void addParagraphs(List<Paragraph> paragraphs);

  /**
   * Adds the given paragraph to this element.
   * 
   * @param paragraph
   *        The paragraph to add.
   */
  void addParagraph(Paragraph paragraph);
}
