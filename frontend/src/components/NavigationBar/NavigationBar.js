import { Link } from "react-router-dom";
import classes from "./navigationbar.module.css";

function NavigationBar() {
	return (
		<nav>
			<ul className={classes.linkList}>
				<li className={classes.listItem}>
					<Link to="/profile">Profile</Link>
				</li>
				<li className={classes.listItem}>
					<Link to="/register">Register</Link>
				</li>
				<li className={classes.listItem}>
					<Link to="/login">Login</Link>
				</li>
			</ul>
		</nav>
	);
}

export default NavigationBar;
