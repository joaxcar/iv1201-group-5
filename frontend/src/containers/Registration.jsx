import { Container, TextField, Grid, Typography } from "@material-ui/core";

function Registration() {
	return (
		<Container>
			<Typography variant="h4">Register</Typography>
			<form>
				<Grid container direction="column" spacing={2}>
					<Grid item>
						<TextField label="First name" />
					</Grid>
					<Grid item>
						<TextField label="Last name" />
					</Grid>
					<Grid item>
						<TextField label="E-mail address" />
					</Grid>
					<Grid item>
						<TextField
							type="date"
							label="Date of birth"
							InputLabelProps={{
								shrink: true,
							}}
						/>
					</Grid>
					<Grid item>
						<TextField label="Username" />
					</Grid>
					<Grid item>
						<TextField label="Password" type="password" />
					</Grid>
				</Grid>
			</form>
		</Container>
	);
}

export default Registration;
