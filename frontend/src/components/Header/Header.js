import { Typography, Container } from "@material-ui/core";
import NavigationBar from "../NavigationBar/NavigationBar";
import classes from "./header.module.css";

/**
 * The header. To be used on all screens.
 */
function Header() {
	return (
		<header className={classes.header}>
			<Container>
				<Typography variant="h3">Amusement Park</Typography>
				<NavigationBar />
			</Container>
		</header>
	);
}

export default Header;
