1. Domain Driven Sizing: Specific domain.Sets the logical borders.High Cohesion
2. Event Storming Sizing:Sets the functional borders.Loose Coupling
3. Strangler Fig Pattern
4. VM vs Container vs Software Container vs Docker vs Kubernetes
5. Generating docker images:
    1. Dockerfile 
    2. Buildpacks: Run command "`mvn spring-boot:build-image`"
    ```xml
            <plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<image>
						<name>loans:m3</name>
					</image>
				</configuration>
			</plugin>
    ```
    3. google jib