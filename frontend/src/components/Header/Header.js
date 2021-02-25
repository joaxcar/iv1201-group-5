import { Typography, Container } from "@material-ui/core";
import classes from "./header.module.css";

/**
 * The header. To be used on all screens.
 */
function Header() {
	return (
		<header className={classes.header}>
			<Container>
				<Typography variant="h3">Amusement Park</Typography>
			</Container>
		</header>
	);
}

export default Header;
