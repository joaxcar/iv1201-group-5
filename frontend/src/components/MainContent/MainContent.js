import classes from "./mainContent.module.css";

/**
 * A wrapper for the main content. Renders any components that
 * is passed into it as children prop.
 * @param {*} children Any main content that should be rendered.
 */
function MainContent({ children }) {
	return <main className={classes.main}>{children}</main>;
}

export default MainContent;
