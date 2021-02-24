import Header from "../Header/Header";
import MainContent from "../MainContent/MainContent";
import Footer from "../Footer/Footer";

import classes from "./layout.module.css";

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
