package icecite.parser.pdfbox.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.fontbox.afm.AFMParser;
import org.apache.fontbox.afm.CharMetric;
import org.apache.fontbox.afm.FontMetrics;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import icecite.parser.pdfbox.core.PdfBoxFontMetricsWrapper;
import icecite.utils.font.PdfFontUtils;
import icecite.utils.path.PathUtils;

/**
 * A collection of utility methods to manage and process AFM files.
 * 
 * @author Claudius Korzen
 */
public class PdfBoxAFMUtils {
  /**
   * The additional glyphs.
   */
  protected static Map<String, PdfBoxFontMetricsWrapper> additionalAFMs;

  static {
    additionalAFMs = readAdditionalAFMFiles("afm/");
  }

  /**
   * Returns the CharMetric for the glyph given by glyphName.
   * 
   * @param glyphName
   *        The name of glyph.
   * @param type1Font
   *        The font.
   * @return The CharMetric.
   */
  public static CharMetric getCharMetric(String glyphName,
      PDType1Font type1Font) {
    if (type1Font != null) {
      String basename = PdfFontUtils.computeBasename(type1Font);

      PdfBoxFontMetricsWrapper fontMetrics = additionalAFMs.get(basename);
      if (fontMetrics != null) {
        return fontMetrics.getCharMetricsMap().get(glyphName);
      }
    }
    return null;
  }

  /**
   * Reads additional AFM files in addition to the 14 standard fonts.
   * 
   * @param path
   *        The path to the directory of AFM files.
   * 
   * @return The map containing the read font metrics.
   */
  protected static Map<String, PdfBoxFontMetricsWrapper> readAdditionalAFMFiles(
      String path) {
    Map<String, PdfBoxFontMetricsWrapper> result = new HashMap<>();
    try {
      Map<String, InputStream> files = PathUtils.readDirectory(path);

      for (Entry<String, InputStream> file : files.entrySet()) {
        String name = file.getKey();
        try (InputStream stream = file.getValue()) {
          String basename = PathUtils.getBasename(name);

          try {
            AFMParser parser = new AFMParser(stream);
            FontMetrics fontMetrics = parser.parse();

            // Put the result into map.
            result.put(basename, new PdfBoxFontMetricsWrapper(fontMetrics));
          } catch (IOException e) {
            continue;
          }
        }
      }
    } catch (IOException e) {
      // TODO
    }

    return result;
  }

}