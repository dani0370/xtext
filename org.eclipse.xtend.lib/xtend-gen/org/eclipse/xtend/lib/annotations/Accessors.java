package org.eclipse.xtend.lib.annotations;

import com.google.common.annotations.GwtCompatible;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.AccessorsProcessor;
import org.eclipse.xtend.lib.macro.Active;

/**
 * Creates getters and setters for annotated fields or for all fields in an annotated class.
 * <p>
 * Annotated on a field
 * <ul>
 * <li>Creates a getter for that field if none exists. For primitive boolean properties, the "is"-prefix is used.</li>
 * <li>Creates a setter for that field if it is not final and no setter exists</li>
 * <li>By default the accessors are public</li>
 * <li>If the {@link AccessorType}[] argument is given, only the listed
 * accessors with the specified visibility will be generated</li>
 * </ul>
 * </p>
 * <p>
 * Annotated on a class
 * <ul>
 * <li>Creates accessors for all non-static fields of that class as specified
 * above</li>
 * <li>Creates a constructor taking all final fields of the class if no
 * constructor exists yet. If there already is a constructor and you want the
 * default one on top of that, you can use the {@link FinalFieldsConstructor}
 * annotation.</li>
 * </ul>
 * </p>
 * Field-level annotations have precedence over a class-level annotation. Accessors can be suppressed completely by using {@link AccessorType#NONE}.
 * This annotation can also be used to fine-tune the getters generated by {@link Data}.
 * @since 2.7
 */
@GwtCompatible
@Target({ ElementType.FIELD, ElementType.TYPE })
@Active(AccessorsProcessor.class)
@Documented
@SuppressWarnings("all")
public @interface Accessors {
  /**
   * Describes the access modifiers for generated accessors. Valid combinations
   * include at most one type for getters and one for setters.
   * Accessors may be suppressed by passing {@link AccessorType#NONE}.
   */
  public AccessorType[] value() default { AccessorType.PUBLIC_GETTER, AccessorType.PUBLIC_SETTER };
}
