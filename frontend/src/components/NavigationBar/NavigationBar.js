import { useContext } from "react";
import { Link } from "react-router-dom";
import { UserContext } from "../../App";
import classes from "./navigationbar.module.css";

/**
 * Navigation menu for the application.
 */
function NavigationBar() {
	const { signedInState, logout } = useContext(UserContext);
	return (
		<nav>
			<ul className={classes.linkList}>
				<li className={classes.listItem}>
					<Link to="/profile">Profile</Link>
				</li>
				{signedInState.isSignedIn ? (
					<li className={classes.listItem}>
						<button onClick={logout}>logout</button>
					</li>
				) : (
					<>
						<li className={classes.listItem}>
							<Link to="/register">Register</Link>
						</li>
						<li className={classes.listItem}>
							<Link to="/login">Login</Link>
						</li>
					</>
				)}
			</ul>
		</nav>
	);
}

export default NavigationBar;
