import Header from "../Header/Header";
import MainContent from "../MainContent/MainContent";
import Footer from "../Footer/Footer";

import classes from "./layout.module.css";

/**
 * Organizes the layout of the application with a header on top, a footer
 * in the bottom and main content in between.
 * @param {*} children The content that is considered to be main content.
 */
function Layout({ children }) {
	return (
		<div className={classes.wrapper}>
			<Header />
			<MainContent>{children}</MainContent>
			<Footer />
		</div>
	);
}

export default Layout;
