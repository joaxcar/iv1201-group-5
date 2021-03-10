import { Container, Typography } from "@material-ui/core";
import classes from "./profile.module.css";

/**
 * Profile page for a signed in user
 * @param {number} accountId - The account id for the user
 */
function Profile({ user }) {
	return (
		<Container>
			<div className={classes.profile}>
				<Typography variant="h4">Profile</Typography>
				{user && (
					<div>
						<p>{user.name}</p>
						<p>{user.birthDate}</p>
						<p>{user.email}</p>
					</div>
				)}
			</div>
		</Container>
	);
}

export default Profile;
