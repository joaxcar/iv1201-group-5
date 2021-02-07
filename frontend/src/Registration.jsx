import {
	Container,
	TextField,
	Grid,
	Typography,
	Button,
} from "@material-ui/core";

function Registration() {
	return (
		<Container maxWidth="sm">
			<Typography variant="h4">Register</Typography>
			<form>
				<Grid container spacing={2}>
					<Grid item xs={12} sm={6}>
						<TextField label="First name" fullWidth />
					</Grid>
					<Grid item xs={12} sm={6}>
						<TextField label="Last name" fullWidth />
					</Grid>
					<Grid item xs={12}>
						<TextField label="E-mail address" fullWidth />
					</Grid>
					<Grid item xs={6} md={4}>
						<TextField label="Year of birth"></TextField>
					</Grid>
					<Grid item xs={6} md={4}>
						<TextField label="Month of birth"></TextField>
					</Grid>
					<Grid item xs={6} md={4}>
						<TextField label="Day of birth"></TextField>
					</Grid>
					<Grid item xs={12}>
						<TextField label="Username" fullWidth />
					</Grid>
					<Grid item xs={12}>
						<TextField label="Password" type="password" fullWidth />
					</Grid>
					<Grid item>
						<Button variant="contained" type="submit">
							Register
						</Button>
					</Grid>
				</Grid>
			</form>
		</Container>
	);
}

export default Registration;
