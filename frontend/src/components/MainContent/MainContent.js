import classes from "./mainContent.module.css";

function MainContent({ children }) {
	return <main className={classes.main}>{children}</main>;
}

export default MainContent;
