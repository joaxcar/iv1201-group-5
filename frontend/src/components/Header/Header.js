import { Typography, Container } from "@material-ui/core";
import classes from "./header.module.css";

function Header() {
	return (
		<header className={classes.header}>
			<Container>
				<Typography variant="h2">Amusement Park</Typography>
			</Container>
		</header>
	);
}

export default Header;
