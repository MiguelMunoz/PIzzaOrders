package com.disney.miguelmunoz.challenge.serial;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.disney.miguelmunoz.challenge.entities.PojoUtility;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * <p>Created by IntelliJ IDEA.
 * <p>Date: 2/13/18
 * <p>Time: 8:04 PM
 *
 * @author Miguel Mu\u00f1oz
 */
public class JsonDateDeserializer extends JsonDeserializer<Date> {
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat(PojoUtility.TIME_FORMAT);
//  private static final SimpleDateFormat dateFormat = new SimpleDateFormat(PojoUtility.TIME_FORMAT);

  /**
   * Method that can be called to ask implementation to deserialize
   * JSON content into the value type this serializer handles.
   * Returned instance is to be constructed by method itself.
   * <p>
   * Pre-condition for this method is that the parser points to the
   * first event that is part of value to deserializer (and which
   * is never JSON 'null' literal, more on this below): for simple
   * types it may be the only value; and for structured types the
   * Object start marker or a FIELD_NAME.
   * </p>
   * <p>
   * The two possible input conditions for structured types result
   * from polymorphism via fields. In the ordinary case, Jackson
   * calls this method when it has encountered an OBJECT_START,
   * and the method implementation must advance to the next token to
   * see the first field name. If the application configures
   * polymorphism via a field, then the object looks like the following.
   * <pre>
   *      {
   *          "@class": "class name",
   *          ...
   *      }
   *  </pre>
   * Jackson consumes the two tokens (the <tt>@class</tt> field name
   * and its value) in order to learn the class and select the deserializer.
   * Thus, the stream is pointing to the FIELD_NAME for the first field
   * after the @class. Thus, if you want your method to work correctly
   * both with and without polymorphism, you must begin your method with:
   * <pre>
   *       if (jp.getCurrentToken() == JsonToken.START_OBJECT) {
   *         jp.nextToken();
   *       }
   *  </pre>
   * This results in the stream pointing to the field name, so that
   * the two conditions align.
   * <p>
   * Post-condition is that the parser will point to the last
   * event that is part of deserialized value (or in case deserialization
   * fails, event that was not recognized or usable, which may be
   * the same event as the one it pointed to upon call).
   * <p>
   * Note that this method is never called for JSON null literal,
   * and thus deserializers need (and should) not check for it.
   *
   * @param jsonParser    Parsed used for reading JSON content
   * @param context Context that can be used to access information about
   *             this deserialization activity.
   * @return Deserialized value
   */
  @Override
  public Date deserialize(final JsonParser jsonParser, final DeserializationContext context) throws IOException {
    String date = jsonParser.getText();
    try {
      return dateFormat.parse(date);
    } catch (ParseException ignored) { }
    try {
      return dateFormat.parse(date);
    } catch (ParseException e) {
      //noinspection ProhibitedExceptionThrown
      throw new RuntimeException(e);
    }
  }
}
