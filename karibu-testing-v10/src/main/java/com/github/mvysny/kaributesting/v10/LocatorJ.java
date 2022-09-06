package com.github.mvysny.kaributesting.v10;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

import kotlin.Unit;

/**
 * To use this class, don't forget to add <code>import static com.github.karibu.testing.LocatorJ.*;</code> to your test class.
 *
 * @author mavi
 */
public class LocatorJ {
    /**
     * Finds a VISIBLE component of given type which matches given class. {@link UI#getCurrent()} all of its descendants are searched.
     *
     * @param clazz the component class
     * @return the only matching component, never null.
     * @throws IllegalArgumentException if no component matched, or if more than one component matches.
     */
    @NotNull
    public static <T extends Component> T _get(@NotNull Class<T> clazz) {
        return LocatorKt._get(clazz, searchSpec -> Unit.INSTANCE);
    }

    /**
     * Finds a VISIBLE component in the current UI of given clazz which matches given spec. The {@link UI#getCurrent()} and all of its descendants are searched.
     * <p>
     * Example:
     * <code>import static com.github.karibu.testing.LocatorJ.*; _get(TextField.class, spec -&gt; spec.withCaption("Name:").withId("name"));</code>
     * </p>
     *
     * @param clazz the component must be of this class.
     * @param spec  allows you to add search criterion.
     * @return the only matching component, never null.
     * @throws IllegalArgumentException if no component matched, or if more than one component matches.
     */
    @NotNull
    public static <T extends Component> T _get(@NotNull Class<T> clazz, @NotNull Consumer<SearchSpecJ<T>> spec) {
        return LocatorKt._get(clazz, ss -> {
            spec.accept(new SearchSpecJ<>(ss));
            return Unit.INSTANCE;
        });
    }

    /**
     * Finds a VISIBLE component of given type which matches given class. The receiver and all of its descendants are searched.
     *
     * @param receiver the parent layout to search in, not null.
     * @param clazz    the component class
     * @return the only matching component, never null.
     * @throws IllegalArgumentException if no component matched, or if more than one component matches.
     */
    @NotNull
    public static <T extends Component> T _get(@NotNull Component receiver, @NotNull Class<T> clazz) {
        return LocatorKt._get(receiver, clazz, searchSpec -> Unit.INSTANCE);
    }

    /**
     * Finds a VISIBLE component of given clazz which matches given spec. The receiver and all of its descendants are searched.
     * <p></p>
     * Example:
     * <code>import static com.github.karibu.testing.LocatorJ.*; _get(layout, TextField.class, spec -&gt; spec.withCaption("Name:").withId("name"));</code>
     *
     * @param receiver the parent layout to search in, not null.
     * @param clazz    the component must be of this class.
     * @param spec     allows you to add search criterion.
     * @return the only matching component, never null.
     * @throws IllegalArgumentException if no component matched, or if more than one component matches.
     */
    @NotNull
    public static <T extends Component> T _get(@NotNull Component receiver, @NotNull Class<T> clazz, @NotNull Consumer<SearchSpecJ<T>> spec) {
        return LocatorKt._get(receiver, clazz, ss -> {
            spec.accept(new SearchSpecJ<>(ss));
            return Unit.INSTANCE;
        });
    }

    /**
     * Clicks the button, but only if it is actually possible to do so by the user. If the button is read-only or disabled, an exception is thrown.
     *
     * @throws IllegalStateException if the button was not visible or not enabled.
     */
    public static void _click(@NotNull Button receiver) {
        ButtonKt._click(receiver);
    }

    /**
     * Sets the value of given component, but only if it is actually possible to do so by the user.
     * If the component is read-only or disabled, an exception is thrown.
     * <p></p>
     * The function fires the value change event; the {@link HasValue.ValueChangeEvent#isFromClient()} will
     * return false indicating that the event came from the server. If this is not desired,
     * depending on your code, it may be
     * possible to call {@link #_fireValueChange(AbstractField, boolean)} with <code>fromClient=true</code> instead.
     * @throws IllegalStateException if the field was not visible, not enabled or was read-only.
     */
    public static <V> void _setValue(@NotNull HasValue<?, V> receiver, @Nullable V value) {
        HasValueUtilsKt.set_value(receiver, value);
    }

    /**
     * Fires a value change event which "comes from the client".
     * <p></p>
     * The event is only fired if it is actually possible to do so by the user.
     * If the component is read-only or disabled, an exception is thrown.
     * @param fromClient whether the event comes from the client or not.
     * @throws IllegalStateException if the field was not visible, not enabled or was read-only.
     */
    public static <C extends AbstractField<C, V>, V> void _fireValueChange(@NotNull C receiver, boolean fromClient) {
        HasValueUtilsKt._fireValueChange(receiver, fromClient);
    }

    /**
     * Fires a value change event which "comes from the client".
     * <p></p>
     * The event is only fired if it is actually possible to do so by the user.
     * If the component is read-only or disabled, an exception is thrown.
     * @throws IllegalStateException if the field was not visible, not enabled or was read-only.
     */
    public static <C extends AbstractField<C, V>, V> void _fireValueChange(@NotNull C receiver) {
        _fireValueChange(receiver, true);
    }

    /**
     * Finds a list of VISIBLE components of given class. {@link UI#getCurrent()} and all of its descendants are searched.
     * @param clazz the requested type of returned components.
     *
     * @return the list of matching components, may be empty.
     */
    @NotNull
    public static <T extends Component> List<T> _find(@NotNull Class<T> clazz) {
        return LocatorKt._find(clazz, spec -> Unit.INSTANCE);
    }

    /**
     * Finds a list of VISIBLE components of given class. {@link UI#getCurrent()} and all of its descendants are searched.
     * @param clazz the requested type of returned components.
     * @return the list of matching components, may be empty.
     */
    @NotNull
    public static <T extends Component> List<T> _find(@NotNull Class<T> clazz, @NotNull Consumer<SearchSpecJ<T>> spec) {
        return LocatorKt._find(clazz, ss -> {
            spec.accept(new SearchSpecJ<>(ss));
            return Unit.INSTANCE;
        });
    }

    /**
     * Finds a list of VISIBLE components of given class. Given component and all of its descendants are searched.
     * @param receiver search this component and all of its descendants.
     * @param clazz the requested type of returned components.
     * @return the list of matching components, may be empty.
     */
    @NotNull
    public static <T extends Component> List<T> _find(@NotNull Component receiver, @NotNull Class<T> clazz) {
        return LocatorKt._find(receiver, clazz, spec -> Unit.INSTANCE);
    }

    /**
     * Finds a list of VISIBLE components of given class which matches given spec. Given component and all of its descendants are searched.
     * @param receiver search this component and all of its descendants.
     * @param clazz the requested type of returned components.
     * @param spec configures the search criteria.
     * @return the list of matching components, may be empty.
     */
    @NotNull
    public static <T extends Component> List<T> _find(@NotNull Component receiver, @NotNull Class<T> clazz, @NotNull Consumer<SearchSpecJ<T>> spec) {
        return LocatorKt._find(receiver, clazz, ss -> {
            spec.accept(new SearchSpecJ<>(ss));
            return Unit.INSTANCE;
        });
    }

    /**
     * Expects that there are no VISIBLE components in the current UI of given class. The {@link UI#getCurrent()} and all of its descendants are searched.
     * @param clazz the requested type of matched components.
     * @throws IllegalArgumentException if one or more components matched.
     */
    public static <T extends Component> void _assertNone(@NotNull Class<T> clazz) {
        LocatorKt._expectNone(clazz, spec -> Unit.INSTANCE);
    }

    /**
     * Expects that there are no VISIBLE components in the current UI of given class which matches spec. The {@link UI#getCurrent()} and all of its descendants are searched.
     * @param clazz the requested type of matched components.
     * @param spec configures the search criteria.
     * @throws IllegalArgumentException if one or more components matched.
     */
    public static <T extends Component> void _assertNone(@NotNull Class<T> clazz, @NotNull Consumer<SearchSpecJ<T>> spec) {
        LocatorKt._expectNone(clazz, ss -> {
            spec.accept(new SearchSpecJ<>(ss));
            return Unit.INSTANCE;
        });
    }

    /**
     * Expects that there are no VISIBLE components of given class. The receiver component and all of its descendants are searched.
     * @param receiver search this component and all of its descendants.
     * @param clazz the requested type of returned components.
     * @throws IllegalArgumentException if one or more components matched.
     */
    public static <T extends Component> void _assertNone(@NotNull Component receiver, @NotNull Class<T> clazz) {
        LocatorKt._expectNone(receiver, clazz, ss -> Unit.INSTANCE);
    }

    /**
     * Expects that there are no VISIBLE components of given class matching given spec. The receiver component and all of its descendants are searched.
     * @param receiver search this component and all of its descendants.
     * @param clazz the requested type of returned components.
     * @param spec configures the search criteria.
     * @throws IllegalArgumentException if one or more components matched.
     */
    public static <T extends Component> void _assertNone(@NotNull Component receiver, @NotNull Class<T> clazz, @NotNull Consumer<SearchSpecJ<T>> spec) {
        LocatorKt._expectNone(receiver, clazz, ss -> {
            spec.accept(new SearchSpecJ<>(ss));
            return Unit.INSTANCE;
        });
    }

    /**
     * Expects that there is exactly ono VISIBLE components in the current UI of given class. The {@link UI#getCurrent()} and all of its descendants are searched.
     * @param clazz    the component must be of this class.
     * @throws AssertionError if none, or more than one components matched.
     */
    public static <T extends Component> void _assertOne(@NotNull Class<T> clazz) {
        LocatorKt._expectOne(clazz, spec -> Unit.INSTANCE);
    }

    /**
     * Expects that there is exactly one VISIBLE components in the current UI of given class which matches spec. The {@link UI#getCurrent()} and all of its descendants are searched.
     * @param clazz    the component must be of this class.
     * @param spec     allows you to add search criterion.
     * @throws AssertionError if none, or more than one components matched.
     */
    public static <T extends Component> void _assertOne(@NotNull Class<T> clazz, @NotNull Consumer<SearchSpecJ<T>> spec) {
        LocatorKt._expectOne(clazz, ss -> {
            spec.accept(new SearchSpecJ<>(ss));
            return Unit.INSTANCE;
        });
    }

    /**
     * Expects that there is exactly one VISIBLE components of given class. The receiver component and all of its descendants are searched.
     * @param receiver the parent layout to search in, not null.
     * @param clazz    the component must be of this class.
     * @throws AssertionError if none, or more than one components matched.
     */
    public static <T extends Component> void _assertOne(@NotNull Component receiver, @NotNull Class<T> clazz) {
        LocatorKt._expectOne(receiver, clazz, ss -> Unit.INSTANCE);
    }

    /**
     * Expects that there is exactly one VISIBLE components of given class matching given spec. The receiver component and all of its descendants are searched.
     * @param receiver the parent layout to search in, not null.
     * @param clazz    the component must be of this class.
     * @param spec     allows you to add search criterion.
     * @throws AssertionError if none, or more than one components matched.
     */
    public static <T extends Component> void _assertOne(@NotNull Component receiver, @NotNull Class<T> clazz, @NotNull Consumer<SearchSpecJ<T>> spec) {
        LocatorKt._expectOne(receiver, clazz, ss -> {
            spec.accept(new SearchSpecJ<>(ss));
            return Unit.INSTANCE;
        });
    }

    /**
     * Expects that there are exactly {@code count} VISIBLE components in the current UI match {@code block}. The {@link UI#getCurrent} and all of its descendants are searched.
     * @param clazz the component must be of this class.
     * @param count this count of components must match
     * @throws AssertionError if incorrect count of component matched.
     */
    public static <T extends Component> void _assert(@NotNull Class<T> clazz, int count) {
        LocatorKt._expect(clazz, count, spec -> Unit.INSTANCE);
    }

    /**
     * Expects that there are exactly {@code count} VISIBLE components in the current UI of given class which matches spec. The {@link UI#getCurrent()} and all of its descendants are searched.
     * @param clazz    the component must be of this class.
     * @param count this count of components must match
     * @param spec     allows you to add search criterion.
     * @throws AssertionError if incorrect count of component matched.
     */
    public static <T extends Component> void _assert(@NotNull Class<T> clazz, int count, @NotNull Consumer<SearchSpecJ<T>> spec) {
        LocatorKt._expect(clazz, count, ss -> {
            spec.accept(new SearchSpecJ<>(ss));
            return Unit.INSTANCE;
        });
    }

    /**
     * Expects that there are exactly {@code count} VISIBLE components of given class. The receiver component and all of its descendants are searched.
     * @param receiver the parent layout to search in, not null.
     * @param clazz    the component must be of this class.
     * @param count this count of components must match
     * @throws AssertionError if incorrect count of component matched.
     */
    public static <T extends Component> void _assert(@NotNull Component receiver, @NotNull Class<T> clazz, int count) {
        LocatorKt._expect(receiver, clazz, count, ss -> Unit.INSTANCE);
    }

    /**
     * Expects that there are exactly {@code count} VISIBLE components of given class matching given spec. The receiver component and all of its descendants are searched.
     * @param receiver the parent layout to search in, not null.
     * @param clazz    the component must be of this class.
     * @param count this count of components must match
     * @param spec     allows you to add search criterion.
     * @throws AssertionError if incorrect count of component matched.
     */
    public static <T extends Component> void _assert(@NotNull Component receiver, @NotNull Class<T> clazz, int count, @NotNull Consumer<SearchSpecJ<T>> spec) {
        LocatorKt._expect(receiver, clazz, count, ss -> {
            spec.accept(new SearchSpecJ<>(ss));
            return Unit.INSTANCE;
        });
    }

    /**
     * Expects that there are no dialogs shown.
     */
    public static void _assertNoDialogs() {
        LocatorKt._expectNoDialogs();
    }

    /**
     * Fails if given component is not {@link HasEnabled#isEnabled()}.
     * May succeed when the parent is disabled.
     * @param component the component to check
     */
    public static void _assertEnabled(@NotNull Component component) {
        BasicUtilsKt._expectEnabled(component);
    }

    /**
     * Fails if given component is {@link HasEnabled#isEnabled()}.
     * May succeed when the parent is disabled.
     * @param component the component to check
     */
    public static void _assertDisabled(@NotNull Component component) {
        BasicUtilsKt._expectDisabled(component);
    }
}
