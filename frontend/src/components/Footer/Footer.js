import { Typography, Container } from "@material-ui/core";
import classes from "./footer.module.css";

/**
 * The footer of the application, to be used on all screens.
 */
function Footer() {
	return (
		<footer className={classes.footer}>
			<Container>
				<Typography variant="h6">
					Created by Group Five Productions
				</Typography>
			</Container>
		</footer>
	);
}

export default Footer;
